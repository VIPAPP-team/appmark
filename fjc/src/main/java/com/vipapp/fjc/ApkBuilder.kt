package com.vipapp.fjc

import android.content.res.AssetManager
import android.util.Log
import java.io.File
import java.io.IOException
import java.io.PrintWriter
import java.io.StringWriter
import kellinwood.security.zipsigner.ZipSigner
import kellinwood.security.zipsigner.optional.CustomKeySigner
import org.spongycastle.jce.provider.BouncyCastleProvider
import java.security.Security.addProvider
import android.R.attr.mode

data class Library(
        val jar_path: String,
        val android_package: String?
)
data class ApkBuilderCert(
        val keystore: String,
        val keystorePassword: String,
        val keystoreAlias: String,
        val keystoreAliasPassword: String
)
data class ApkBuilderConfig(
        val aapt: String,
        val android_jar: String,
        val src: String,
        val res: String,
        val assets: String?,
        val manifest: String,
        val libs: Array<Library>?,
        val libsFolder: String?,
        val build: String,
        val cert: ApkBuilderCert?
)
interface ApkBuilderCallBack {
    fun aapt()
    fun aaptOK(output: String)
    fun aaptERR(code: Int, output: String)
    fun java()
    fun javaOK(output_out: String, output_err: String)
    fun javaERR(output_out: String, output_err: String)
    fun dx()
    fun dxOK(output: String)
    fun dxERR(code: Int)
    //TODO fun merge()
    fun pkg()
    fun sign()
    fun OK(output: String)
    fun ERR(exception: java.lang.Exception)
    fun apk(path: String)
}
object ApkBuilderInit {
    fun initAAPT(assets: AssetManager, path: String) {
        copyAsset(assets, "aapt", "$path/aapt")
        File("$path/aapt").setExecutable(true, false)
    }
    fun copyAndroidJar(assets: AssetManager, path: String) = copyAsset(assets, "android.jar", "$path/android.jar")
    private fun copyAsset(assetManager: AssetManager, path: String, target: String) {
        val input = assetManager.open(path)
        File(target).writeBytes(input.readBytes())
        input.close()
    }
}
class ApkBuilder(val config: ApkBuilderConfig, val callBack: ApkBuilderCallBack) {
    var running = true
    fun build() {
        Thread {
            try {
                File(config.build).mkdirs()
                File(config.build, "/gen/").mkdirs()
                callBack.aapt()
                if (running) aapt()
                callBack.java()
                if (running) java()
                callBack.dx()
                if (running) dx()
                //TODO if(running) merge()
                callBack.pkg()
                if (running) pkg()
                callBack.sign()
                if (running) sign()
            } catch (e: Exception) {
                callBack.ERR(e)
            }
        }.start()
    }
    fun aapt() {
        var libs_pkg = ""
        var libs_jar = ""
        if (config.libs != null) for (lib in config.libs) {
            if (lib.android_package != null) libs_pkg += lib.android_package
            libs_jar += lib.jar_path
        }
        val args = mutableListOf<String>()
        args.addAll(
                arrayOf(
                        config.aapt,
                        "package", "-v", "-f", "-m", "--auto-add-overlay",
                        "--no-version-vectors",
                        "-S", config.res
                )
        )
        if (libs_pkg.isNotEmpty()) args.addAll(
                arrayOf(
                        "--extra-packages", libs_pkg
                )
        )
        args.addAll(
                arrayOf(
                        "-J", config.build + "/gen/",
                        "-M", config.manifest,
                        "-I", if (libs_jar.isNotEmpty()) config.android_jar + " " + libs_jar else config.android_jar
                )
        )
        args.addAll(
                arrayOf(
                        "-F", "${config.build}/app.apk.res"
                )
        )
        if (config.assets != null) args.addAll(
                arrayOf(
                        "-A", config.assets
                )
        )
        val aapt = Runtime.getRuntime().exec(args.toTypedArray())
        Log.e("AAaAA", aapt.inputStream.bufferedReader().readText())
        val code = aapt.waitFor()
        if (code == 0) {
            callBack.aaptOK(aapt.inputStream.bufferedReader().readText())
        } else {
            callBack.aaptERR(code, aapt.errorStream.bufferedReader().readText())
            running = false
            return
        }
    }
    fun java() {
        val _out = StringWriter()
        val _err = StringWriter()
        val compiler = org.eclipse.jdt.internal.compiler.batch.Main(PrintWriter(_out), PrintWriter(_err), false, null, null)
        val args = mutableListOf<String>()
        if(config.libsFolder != null) args.addAll(
                arrayOf(
                        "-extdirs", config.libsFolder
                )
        )
        args.addAll(
                arrayOf(
                        "-bootclasspath", config.android_jar,
                        "-classpath",
                        if(config.libsFolder != null)
                            config.src
                            + ":" + config.build+"/gen/"
                            + ":" + config.libsFolder
                        else
                            config.src + ":" + config.build + "/gen/",
                        "-1.7",
                        "-proc:none",
                        "-target", "1.7",
                        "-d", config.build + "/classes/",
                        config.src + "/",
                        config.build + "/gen/" + "/"
                )
        )
        if(compiler.compile(args.toTypedArray())) {
            callBack.javaOK(_out.toString(), _err.toString())
        } else {
            callBack.javaERR(_out.toString(), _err.toString())
            running = false
            return
        }
    }
    fun dx() {
        try {
            val args = arrayOf("--num-threads=${Runtime.getRuntime().availableProcessors()}", "--output=" + config.build + "/classes.dex", config.build + "/classes/")
            val dexArgs = com.androidjarjar.dx.command.dexer.Main.Arguments()
            val mtd = com.androidjarjar.dx.command.dexer.Main.Arguments::class.java.getDeclaredMethod("parse", Array<String>::class.java)
            mtd.isAccessible = true
            mtd.invoke(dexArgs, args)
            val resultCode = com.androidjarjar.dx.command.dexer.Main.run(dexArgs)
            if (resultCode != 0) {
                callBack.dxERR(resultCode)
                running = false
                return
            }
        } catch (e: Exception) {
            callBack.ERR(e)
            running = false
            return
        }
    }
    /*TODO fun merge() {
        try {
            val args = mutableListOf<String>()
            args.add("${config.build}/all_classes.dex")
            File(config.build).list()?.forEach { if (it.endsWith(".dex")) args.add(it) }
            for (i in 0 until args.size) args[i] = File("${config.build}/${args[i]}").absolutePath
            com.androidjarjar.dx.merge.DexMerger.main(args.toTypedArray())
        } catch (e: Exception) {
            callBack.ERR(e)
            running = false
            return
        }
    }*/
    fun pkg() {
        try {
            val builder = com.android.sdklib.build.ApkBuilder(File("${config.build}/app.apk.unsigned"), //The location of the output APK file (unsigned)
                    File("${config.build}/app.apk.res"), //The location of the .apk.res file
                    File("${config.build}/classes.dex"), null, null //Only specify an output stream if we want verbose output
            )
            builder.addSourceFolder(File(config.src))
            builder.sealApk()
        } catch (e: Exception) {
            callBack.ERR(e)
            running = false
            return
        }
    }
    fun sign() {
        val signer = ZipSigner()
        val inFilename = "${config.build}/app.apk.unsigned"
        val outFilename = "${config.build}/app.apk"
        if(config.cert != null) {
            addProvider(BouncyCastleProvider())
            try {
                CustomKeySigner.signZip(signer, config.cert.keystore, config.cert.keystorePassword.toCharArray(), config.cert.keystoreAlias, config.cert.keystoreAliasPassword.toCharArray(), "SHA1WITHRSA", inFilename, outFilename)
            } catch (e: Exception) {
                callBack.ERR(e)
                running = false
                return
            }
        } else {
            try {
                signer.setKeymode("testkey")
                signer.signZip(inFilename, outFilename)
            } catch (e: Exception) {
                callBack.ERR(e)
                running = false
                return
            }
        }
        callBack.apk(outFilename)
    }
}