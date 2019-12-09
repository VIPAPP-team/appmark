package com.vipapp.appmark2.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.item.SettingsItem;
import com.vipapp.appmark2.item.design.DesignAttribute;
import com.vipapp.appmark2.item.design.attribute.BackgroundAttribute;
import com.vipapp.appmark2.item.design.attribute.GravityAttribute;
import com.vipapp.appmark2.item.design.attribute.HeightAttribute;
import com.vipapp.appmark2.item.design.attribute.IdAttribute;
import com.vipapp.appmark2.item.design.attribute.OrientationAttribute;
import com.vipapp.appmark2.item.design.attribute.SrcAttribute;
import com.vipapp.appmark2.item.design.attribute.TextColorAttribute;
import com.vipapp.appmark2.item.design.attribute.VisibilityAttribute;
import com.vipapp.appmark2.item.design.attribute.WidthAttribute;
import com.vipapp.appmark2.item.design.attribute.margin.Margin;
import com.vipapp.appmark2.item.design.attribute.margin.MarginBottom;
import com.vipapp.appmark2.item.design.attribute.margin.MarginEnd;
import com.vipapp.appmark2.item.design.attribute.margin.MarginHorizontal;
import com.vipapp.appmark2.item.design.attribute.margin.MarginLeft;
import com.vipapp.appmark2.item.design.attribute.margin.MarginRight;
import com.vipapp.appmark2.item.design.attribute.margin.MarginStart;
import com.vipapp.appmark2.item.design.attribute.margin.MarginTop;
import com.vipapp.appmark2.item.design.attribute.margin.MarginVertical;
import com.vipapp.appmark2.item.design.attribute.padding.Padding;
import com.vipapp.appmark2.item.design.attribute.padding.PaddingBottom;
import com.vipapp.appmark2.item.design.attribute.padding.PaddingEnd;
import com.vipapp.appmark2.item.design.attribute.padding.PaddingHorizontal;
import com.vipapp.appmark2.item.design.attribute.padding.PaddingLeft;
import com.vipapp.appmark2.item.design.attribute.padding.PaddingRight;
import com.vipapp.appmark2.item.design.attribute.padding.PaddingStart;
import com.vipapp.appmark2.item.design.attribute.padding.PaddingTop;
import com.vipapp.appmark2.item.design.attribute.padding.PaddingVertical;
import com.vipapp.appmark2.item.setting.item.AppVersionName;
import com.vipapp.appmark2.item.setting.item.DebuggerEnabled;
import com.vipapp.appmark2.item.setting.item.LabelItem;
import com.vipapp.appmark2.item.setting.item.MainEditorFontSize;
import com.vipapp.appmark2.item.setting.item.MaxLinesInEditor;
import com.vipapp.appmark2.item.setting.item.OpenLastProject;
import com.vipapp.appmark2.item.setting.item.ServerStatus;
import com.vipapp.appmark2.item.setting.item.SettingTitleItem;
import com.vipapp.appmark2.item.setting.item.ShowHighlightUpdate;
import com.vipapp.appmark2.item.setting.item.UndoOnBackPressed;
import com.vipapp.appmark2.util.wrapper.mAppInfo;
import com.vipapp.appmark2.util.wrapper.mSharedPreferences;
import com.vipapp.appmark2.util.wrapper.Str;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import static android.view.Gravity.BOTTOM;
import static android.view.Gravity.CENTER;
import static android.view.Gravity.CENTER_HORIZONTAL;
import static android.view.Gravity.CENTER_VERTICAL;
import static android.view.Gravity.CLIP_HORIZONTAL;
import static android.view.Gravity.CLIP_VERTICAL;
import static android.view.Gravity.END;
import static android.view.Gravity.FILL;
import static android.view.Gravity.FILL_HORIZONTAL;
import static android.view.Gravity.FILL_VERTICAL;
import static android.view.Gravity.LEFT;
import static android.view.Gravity.RIGHT;
import static android.view.Gravity.START;
import static android.view.Gravity.TOP;

public class Const {

    public static final String PREFS_NAME = "data";

    // CLASS TYPES
    public static final int CLASS_TYPE = 0;
    public static final int INTERFACE_TYPE = 1;
    public static final int ABSTRACT_CLASS_TYPE = 2;
    public static final int LOCAL_CLASS_TYPE = 3;
    public static final int CLASS_NOT_FOUND_TYPE = 4;

    // SERVER CONST
    public static final String TELEGRAM_COMMUNITY_URL = "https://t.me/vipapp_official";

    private static final String SERVER_WEBHOST = "https://apphosting.000webhostapp.com/appmark/";
    private static final String SERVER_NEON = "https://neonteam.net/appmark/";

    private static final String SERVER_URL = SERVER_WEBHOST;
    public static final String SERVER_STATUS = SERVER_URL + "status";
    public static final String SERVER_INFO = SERVER_URL + "info.json";
    public static String SERVER_CHANGELOG = SERVER_URL + "changelog/";

    // SETTING NAMES
    public static final String UNDO_ON_BACK_PRESSED = "undoOnBackPressedSetting0";
    public static final String DEBUGGER_ENABLED_PREF = "debuggerEnabled0";
    public static final String OPEN_LAST_PROJECT_PREF = "openLastProject0";
    public static final String MAIN_EDITOR_FONT_SIZE_PREF = "mainEditorTextSize0";
    public static final String MAX_LINES_IN_TEXT_EDITOR_PREF = "maxLinesInEditor0";
    public static final String HIGHLIGHT_DEBUG_PREF = "highlightDebug0";

    public static final String DEFAULT_APP_NAME = "MyApp";
    public static final String DEFAULT_PACKAGE_NAME = "com.mycompany.myapp";
    public static final String DEFAULT_VERSION_NAME = "1.0";
    public static final String DEFAULT_VERSION_CODE = "1";

    // SETTINGS DEFAULT
    public static final boolean DEFAULT_HIGHLIGHT_DEBUG = false;
    public static final boolean DEFAULT_ON_BACK_PRESSED = false;
    public static final boolean DEFAULT_DEBUGGER_ENABLED = true;
    public static final boolean DEFAULT_OPEN_LAST_PROJECT = true;
    public static final int DEFAULT_MAIN_EDITOR_FONT_SIZE = 15;
    public static final int DEFAULT_MAX_LINES_IN_TEXT_EDITOR = 15;

    public static final int MIN_MAIN_EDITOR_FONT_SIZE = 9;
    public static final int MAX_MAIN_EDITOR_FONT_SIZE = 30;
    public static final int MIN_MAX_LINES_IN_TEXT_EDITOR = 1;
    public static final int MAX_MAX_LINES_IN_TEXT_EDITOR = 30;

    // SETTINGS CONST
    public static boolean DEBUGGER_ENABLED;
    public static boolean HIGHLIGHT_DEBUG;
    public static boolean OPEN_LAST_PROJECT;
    public static int MAIN_EDITOR_FONT_SIZE;
    public static int MAX_LINES_IN_TEXT_EDITOR;

    // FILE TYPES (FOR ACTION BUTTON)
    public static final int FILE_LAYOUT = 0;
    public static final int FILE_STRINGS = 1;

    public static final String RES_XML_OBJ_DEFAULT = "<%1$s name=\"%2$s\">%3$s</%1$s>";

    public static final HashMap<Character, Character> back_symbol = new HashMap<Character, Character>(){{
        put('\'', '\'');
        put('"', '"');
        put('(', ')');
        put('[', ']');
        put('{', '}');
        put('<', '>');
    }};

    public static final HashMap<String, String> ext_to_default_texts = new HashMap<String, String>(){{
        put("java", "texts/default_java.java");
        put("xml", "texts/default_xml.xml");
    }};

    @SuppressLint("RtlHardcoded")
    public static final HashMap<String, Integer> gravity_string_to_int = new HashMap<String, Integer>(){{
        put("center", CENTER);
        put("bottom", BOTTOM);
        put("center_horizontal", CENTER_HORIZONTAL);
        put("center_vertical", CENTER_VERTICAL);
        put("clip_horizontal", CLIP_HORIZONTAL);
        put("clip_vertical", CLIP_VERTICAL);
        put("end", END);
        put("fill", FILL);
        put("fill_horizontal", FILL_HORIZONTAL);
        put("fill_vertical", FILL_VERTICAL);
        put("left", LEFT);
        put("right", RIGHT);
        put("start", START);
        put("top", TOP);
    }};

    public static final int LOAD_TIME = 100;
    public static final int HIGHLIGHT_COUNTDOWN = 2000;
    public static final int UNDO_TIMER_COUNTDOWN = 3000;
    public static final int UNDO_MAX_SIZE = 50;
    public static final int DIFFERENCE_TO_UNDO = 10;

    public static final float DISTANCE_TO_ZOOM = 10;
    public static final float TEXT_SIZE_STEP = 0.5f;

    // CODE PATTERNS
    public static final Pattern STRING_PATTERN = Pattern.compile("\".*?\"");
    public static final Pattern INT_PATTERN = Pattern.compile("\\b(?:0x)?[0-9]+(?:.[0-9]+f?)?\\b");
    // JAVA
    public static final Pattern JAVA_KEYWORDS_PATTERN = Pattern.compile(
            "\\b(this|super|import|package|class|extends|public|protected|private|static|final|int" +
                    "|void|boolean|new|return|try|catch|true|false|interface|null|throws|throw)\\b");
    public static final Pattern COMMENTS_SINGLE_LINE = Pattern.compile("(//)(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$).*");
    public static final Pattern COMMENTS_MULTI_LINE = Pattern.compile("(?:/\\*)(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)(?:.|\\n)*?\\*/");
    public static final Pattern IMPORT_NOT_IMPORTANT = Pattern.compile("(?<=import ).*\\.(?=.*;)");
    public static final Pattern ANNOTATIONS = Pattern.compile("@ *(?:[a-zA-Z_0-9]+(?=\\s?))");
    // XML
    public static final Pattern XML_START_ELEMENT = Pattern.compile("(?<=<)[^/\\s>?]+(?=(?:>|\\s))");
    public static final Pattern XML_END_ELEMENT = Pattern.compile("(?<=</).*?(?=>)");
    public static final Pattern XML_COMMENTS = Pattern.compile("(?:<!--)(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)(?:.|\\n)*?-->");
    public static final Pattern AFTER_XMLNS = Pattern.compile("(?<=xmlns:)[^=]+");
    public static final Pattern XMLNS = Pattern.compile("\\sxmlns:");
    public static final Pattern XML_START_TAG = Pattern.compile("(?<=\\s)[^:\\s=]*?(?=:)");
    public static final Pattern XML_END_TAG = Pattern.compile("\\b.\\w*?(?:=)");

    // Extension to language table
    public static final HashMap<String, Integer> extToLan = new HashMap<String, Integer>(){{
        put("java", JAVA_LANGUAGE);
        put("json", JSON);
        put("aif", JSON);
        put("xml", XML);
    }};

    // LANGUAGE CONST
    public static final int TEXT = 0;
    public static final int JAVA_LANGUAGE = 1;
    public static final int JSON = 2;
    public static final int XML = 3;

    // CODE COLORS
    public static final String LINE_NUMBER_COLOR = "#999999";
    public static final String CURRENT_LINE_COLOR = "#FFE4FF";
    public static final String COMMENTS_COLOR = "#808080";
    public static final String STRING_COLOR = "#008000";
    public static final String INT_COLOR = "#0000ff";
    public static final String KEYWORDS_COLOR = "#000080";
    public static final String ANNOTATIONS_COLOR = "#808000";
    public static final String XML_TAG_COLOR = "#0000FF";
    public static final String XML_TAG_SECOND_COLOR = "#660E7A";

    public static String[] CODE_EDITOR_SYMBOLS = new String[]{
            "    ", ";", "\"", "{", "}", "(", ")", ",", ".", "=", "[", "]", ":", "#", "<", ">"};

    // ARRAYS
    public static ArrayList<Item<String>> goto_chooser = new ArrayList<>();
    public static ArrayList<Item<String>> create_chooser = new ArrayList<>();
    public static ArrayList<Item<String>> file_action_chooser = new ArrayList<>();
    public static ArrayList<Item<String>> code_menu_chooser = new ArrayList<>();
    public static ArrayList<Item<String>> main_menu = new ArrayList<>();
    public static ArrayList<SettingsItem> settingsItems = new ArrayList<>();

    // ITEM TYPES
    public static final int ADD_LOCALE = -1;
    public static final int ANY = 0;
    public static final int PATH = 1;
    public static final int FILE_SELECTED = 2;
    public static final int OPENED_FILE = 3;
    public static final int PROJECT = 4;
    public static final int STRINGS_PUSHED = 5;
    public static final int STRING_NAME_CHANGED = 6;
    public static final int STRING_VALUE_CHANGED = 7;
    public static final int STRING_CREATED = 8;
    public static final int STRINGS_LIST_PUSHED = 9;
    public static final int SYMBOL_INSERTED = 10;
    public static final int PROJECT_MANAGER = 11;

    // STATES CONST
    public final static int STATE_LOADING = 0;
    public final static int STATE_RUNNING = 1;
    public final static int STATE_NO_PROJECTS = 2;

    // ATTRS CONST
    public static final String ID_ATTR = "android:id";
    public static final String WIDTH_ATTR = "android:layout_width";
    public static final String HEIGHT_ATTR = "android:layout_height";
    public static final String GRAVITY_ATTR = "android:gravity";
    public static final String BACKGROUND_ATTR = "android:background";
    public static final String MARGIN = "android:layout_margin";
    public static final String MARGIN_LEFT = "android:layout_marginLeft";
    public static final String MARGIN_RIGHT = "android:layout_marginRight";
    public static final String MARGIN_TOP = "android:layout_marginTop";
    public static final String MARGIN_BOTTOM = "android:layout_marginStart";
    public static final String MARGIN_HORIZONTAL = "android:layout_marginStart";
    public static final String MARGIN_VERTICAL = "android:layout_marginStart";
    public static final String MARGIN_START = "android:layout_marginStart";
    public static final String MARGIN_END = "android:layout_marginEnd";
    public static final String PADDING = "android:padding";
    public static final String PADDING_LEFT = "android:paddingLeft";
    public static final String PADDING_RIGHT = "android:paddingRight";
    public static final String PADDING_TOP = "android:paddingTop";
    public static final String PADDING_BOTTOM = "android:paddingBottom";
    public static final String PADDING_HORIZONTAL = "android:paddingHorizontal";
    public static final String PADDING_VERTICAL = "android:paddingVertical";
    public static final String PADDING_START = "android:paddingStart";
    public static final String PADDING_END = "android:paddingEnd";
    public static final String ORIENTATION_ATTR = "android:orientation";
    public static final String TEXT_COLOR_ATTR = "android:textColor";
    public static final String VISIBILITY_ATTR = "android:visibility";
    public static final String SRC_ATTR = "android:src";

    public static final ArrayList<DesignAttribute> ATTRIBUTES = new ArrayList<DesignAttribute>(){{
        add(new BackgroundAttribute());
        add(new Margin());
        add(new MarginLeft());
        add(new MarginRight());
        add(new MarginBottom());
        add(new MarginTop());
        add(new MarginStart());
        add(new MarginEnd());
        add(new MarginHorizontal());
        add(new MarginVertical());
        add(new Padding());
        add(new PaddingLeft());
        add(new PaddingRight());
        add(new PaddingBottom());
        add(new PaddingTop());
        add(new PaddingStart());
        add(new PaddingEnd());
        add(new PaddingHorizontal());
        add(new PaddingVertical());
        add(new TextColorAttribute());
        add(new GravityAttribute());
        add(new HeightAttribute());
        add(new WidthAttribute());
        add(new IdAttribute());
        add(new OrientationAttribute());
        add(new VisibilityAttribute());
        add(new SrcAttribute());
    }};

    // MANIFEST CONST
    public static final String VERSION_NAME = "android:versionName";
    public static final String VERSION_ID = "android:versionCode";
    public static final String PACKAGE = "package";
    public static final String APP_NAME = "android:label";
    public static final String APP_ICON = "android:icon";

    // AIF CONST
    public static final String DANGEROUS_WARN_KEY = "WARN!!! DO NOT MODIFY ANYTHING!!!";
    public static final String DANGEROUS_WARN_VALUE = "ANY ACTIONS WITH THIS FILE CAN PROVIDE APP CRASHED AND MAKE PROJECT UNAVAILABLE";
    public static final String DEFAULT_FILE = "default_file";
    public static final String OPENED_FILE_STRING = "opened_file";

    // ON LOAD CONST
    public static final int PROJECT_LOAD = 0;
    public static final int NO_PROJECTS = 1;
    public static final int GALLERY_LOADED = 2;
    public static final int PROJECT_REMOVED = 3;
    public static final int PROJECT_EDITED = 4;

    // STORAGE CONST
    public static String COMPILER_STORAGE;
    public static String AAPT_STORAGE;
    public static String ANDROID_JAR_STORAGE;

    private static final String APPMARK_INTERNAL = Environment.getExternalStorageDirectory() + "/.appmark/";
    public static final String APPMARK_CACHE = APPMARK_INTERNAL + "cache/";
    public static final String ANDROID_DRAWABLES = APPMARK_CACHE + "/res/";

    public static final String PROJECT_STORAGE = Environment.getExternalStorageDirectory() + "/AppMark/";
    public static final String TMP_IMAGE_FILE = PROJECT_STORAGE + "tmp.png";
    private static final String SRC = "/app/src";
    private static final String WORKSPACE = SRC + "/main";
    public static final String BUILD_DIR = SRC + "/build";
    public static final String ASSETS = WORKSPACE + "/assets";
    public static final String RES = WORKSPACE + "/res";
    public static final String LAYOUT = RES + "/layout";
    public static final String JAVA = WORKSPACE + "/java/%1$s";
    public static final String VALUES = RES + "/values";
    public static final String STRINGS = VALUES + "/strings.xml";
    public static final String DRAWABLES = RES + "/drawable";
    public static final String APP_ICON_DEFAULT = DRAWABLES + "/ic_launcher.png";
    public static final String ANDROID_MANIFEST_LOCATION = WORKSPACE + "/AndroidManifest.xml";
    // AIF DEFAULT CONST
    private static final String DEFAULT_FILE_DEFAULT_VALUE = JAVA + "/MainActivity.java";

    // REGEX CONST
    public static final String REFERENCE_REGEX = "@.+/";
    public static final String LOCALE_REGEX = "[a-z\\-]*";
    public static final String WORD_SPLITTER = "([^.\\w]|\\W(?=\\W))";
    public static final String IMPORTS_REGEX = "(?:package(.|\\n)*?(?=import)|.+\\{(?:.|\\s)*|import.*\\.|;)";
    public static final String VALUES_REGEX = "values(-[a-z]+)?";
    public static final String PACKAGE_REGEX = "^[a-z][a-z0-9_]*(\\.[a-z0-9_]+)+";
    public static final String IMAGE_REGEX = ".+\\.(png|jpg)";
    public static final String TEXT_FILE_REGEX = ".+\\.(xml|aif|java|json)";
    public static final String FILENAME_REGEX = "([a-zA-Z0-9\\s_\\\\.\\-\\(\\):])+";
    public static final String DRAWABLE_REGEX = "%1$s\\.(png|jpg|xml)";
    public static final String PRETTY_XML_NEWLINE = " +(?=[^\\s]+=)";

    // CALLBACK CONST
    public static final int IMAGE_CLICKED = 0;


    public static void init(Context context){
        initArrays(context);
        initWithResources(context);
        initCompiler(context);
        initSettingsConst();
    }

    private static void initArrays(Context context){
        // Goto chooser init
        goto_chooser = new ArrayList<Item<String>>(){{
            add(new Item<>(0, context.getString(R.string.goto_opened)));
            add(new Item<>(1, context.getString(R.string.goto_project)));
        }};
        // Create chooser init
        create_chooser = new ArrayList<Item<String>>(){{
            add(new Item<>(2, context.getString(R.string.create_file)));
            add(new Item<>(3, context.getString(R.string.create_folder)));
            add(new Item<>(4, context.getString(R.string.import_image)));
        }};
        // File action chooser init
        file_action_chooser = new ArrayList<Item<String>>(){{
            add(new Item<>(5, Str.get(R.string.delete)));
            add(new Item<>(6, Str.get(R.string.rename)));
            add(new Item<>(7, Str.get(R.string.copy_path)));
        }};
        // Code menu chooser init
        code_menu_chooser = new ArrayList<Item<String>>(){{
            add(new Item<>(0, Str.get(R.string.run_project)));
            add(new Item<>(3, Str.get(R.string.undo)));
            add(new Item<>(4, Str.get(R.string.redo)));
            add(new Item<>(1, Str.get(R.string.replace)));
            add(new Item<>(2, Str.get(R.string.project_settings)));
            add(new Item<>(5, Str.get(R.string.exit)));
        }};
        // Main screen menu init
        main_menu = new ArrayList<Item<String>>(){{
            add(new Item<>(7, Str.get(R.string.community)));
            add(new Item<>(5, Str.get(R.string.settings)));
            add(new Item<>(6, Str.get(R.string.changelog)));
        }};
        // Settings list init
        settingsItems = new ArrayList<SettingsItem>(){{
            add(new SettingTitleItem(R.string.system_settings));
            add(new OpenLastProject());
            add(new SettingTitleItem(R.string.text_editor_settings));
            add(new MainEditorFontSize());
            add(new MaxLinesInEditor());
            add(new UndoOnBackPressed());
            add(new SettingTitleItem(R.string.settings_debug));
            add(new DebuggerEnabled());
            add(new ShowHighlightUpdate());
            add(new SettingTitleItem(R.string.app_info));
            add(new LabelItem(R.string.version_number, Long.toString(mAppInfo.get().versionCode)));
            add(new AppVersionName());
            add(new ServerStatus());
            add(new SettingTitleItem(R.string.developers));
            add(new LabelItem(R.string.main_developer_description));
            add(new LabelItem(R.string.designer_description));
            add(new LabelItem(R.string.second_programmer));
        }};
    }

    private static void initCompiler(Context c){
        COMPILER_STORAGE = c.getFilesDir().getPath();
        AAPT_STORAGE = COMPILER_STORAGE + "/aapt";
        ANDROID_JAR_STORAGE = COMPILER_STORAGE + "/android.jar";
    }
    private static void initWithResources(Context c){
        SERVER_CHANGELOG += c.getString(R.string.locale);
    }
    public static void initSettingsConst(){
        DEBUGGER_ENABLED = mSharedPreferences.getBoolean(DEBUGGER_ENABLED_PREF, DEFAULT_DEBUGGER_ENABLED);
        OPEN_LAST_PROJECT = mSharedPreferences.getBoolean(OPEN_LAST_PROJECT_PREF, DEFAULT_OPEN_LAST_PROJECT);
        MAIN_EDITOR_FONT_SIZE = mSharedPreferences.getInteger(MAIN_EDITOR_FONT_SIZE_PREF, DEFAULT_MAIN_EDITOR_FONT_SIZE);
        MAX_LINES_IN_TEXT_EDITOR = mSharedPreferences.getInteger(MAX_LINES_IN_TEXT_EDITOR_PREF, DEFAULT_MAX_LINES_IN_TEXT_EDITOR);
        HIGHLIGHT_DEBUG = mSharedPreferences.getBoolean(HIGHLIGHT_DEBUG_PREF, DEFAULT_HIGHLIGHT_DEBUG);
    }

    public static String getDefaultLastFile(String project_package){
        return String.format(DEFAULT_FILE_DEFAULT_VALUE, project_package.replace('.', '/'));
    }

    public static String getJavaDir(String project_package){
        return String.format(JAVA, project_package.replace('.', '/'));
    }

}
