# appmark
Mobile Android IDE

This is android app where you can build your own apps using Java

project: https://github.com/orgs/VIPAPP-team/projects/1

support: https://unikalni4elovek.000webhostapp.com/donate

# struct (IN DEVELOPING):
SOURCE DIRECTORY: appmark/app/src/main/java/com/vipapp/appmark2 <br>

    -activity  # activities storage 
      
      /**
      *    ACTIVITY ARCHITECTURE:
      *  onCreateView() -> int - return argument that will be passed in setContentView()
      *  createView() - additional method to set view
      *  findViews() - findViewById(...) calls
      *  setCallbacks() - set all callbacks
      *  init() - set default values for variables
      *  setup() - set default state for views
      **/
      
      BaseActivity:  # main activity class 

        > callbacks: ArrayList<PushCallback<ActivityResult>>  # list with all onActivityResult callbacks
        > onLoadCallbacks: ArrayList<PushCallback<OnLoadItem>>  # list with all onItemLoad callback 
        
        *exit_with_permission_error() -> exit from app and show permission error
        *onLoad(item: OnLoadItem) -> call all 'onLoadCallback' and onLoadCallback(...)
        
        *addOnActivityResultCallback(callback: ...) -> add callback to 'callbacks'
        *addOnLoadCallback(callback: ...) -> add callback to 'onLoadCallback'
        
        *f(id: int) -> findViewById(id)
        
        - Overridable -
        *onLoadCallback(item: OnLoadItem) in onLoad(...)
        *onCreateView() in onCreate(...)
        *createView() in onCreate(...)
        *findViews() in onCreate(...)
        *setCallbacks() in onCreate(...)
        *init() in onCreate(...)
        *setup() in onCreate(...)
        
        - Overriden -
        *onCreate(...) -> set default orientation and call architecture methods
        *onRequestPermissionResult(...) -> exit from app if any permission rejected or recreate activity
        *onActivityResult(...) -> call all 'callbacks'
        *findViewById(...) -> marked as deprecated to do not forgot use f()

      IntroActivity(BaseActivity) -> activity with introduce animation and permission requesting
      CodeActivity(BaseActivity) -> activity with code editor and file manager
      MainActivity(BaseActivity) -> activity with project manager
      DebugActivity(BaseActivity) -> activity with error view

      StringsEditorActivity(BaseActivity)
      SettingsActivity(BaseActivity)
      ViewDesignActivity(BaseActivity)

    -adapter  # adapters storage

      # it's recommended to use DefaultMenu class to work with recycler
      DefaultAdapter:  # main adapter class that uses DefaultMenu class as support for build

        > menu: DefaultMenu  # support class
        > viewHolderName: String  # view holder class name
        > list: ArrayList  # list with elements
        > xml_source: int  # layout resource
        > recyclerView: RecyclerView  # parent recycler
        > callbacks: ArrayList<PushCallback<Item>>  # item push callbacks

        *pushArray(...) -> support methods to push array from menu to recycler
        *transferObjectToMenu(item: Item) -> support method to push item from recycler to menu
        *onPush(item: Item) -> support method to push item from recycler to menu (execs all 'callbacks')

        *addOnPushCallback(callback: PushCallback<Item>) -> add 'callback' to 'callbacks'

        *onRecyclerPushed(recycler: RecyclerView) -> connect recycler with adapter

        *update() -> update recycler view list by getting new one from menu

        - Overriden -
        *onCreateViewHolder(...) -> set view holder from 'viewHolderName'
        *onBindViewHolder(...) -> call menu.bind(...)
        *getItemViewType(...) -> call menu.getItemViewType(...)
        *getItemCount(...) -> call list.size()
        
    -alert  # alert dialogs storage
      
      -confirm  # confirm alerts dialog storage
      
        DeleteFileDialog(ConfirmDialog) -> dialog with file delete confirmation
        DeleteProject(ConfirmDialog) -> dialog with project delete confirmation
      
      -strings_list_editor  # strings list editor dialogs storage
      
        ProjectSettingsDialog -> dialog with project settings editor
        
      ConfirmDialog:  # dialog with agree and decline button
      
        # show dialog with title 'title_text', button-agree with text 'text_confirm',
        # button-decline with text 'text_cancel' that pushes result into 'result'.onComplete()
        *show(title_text: String, text_confirm: String, text_cancel: String, result: PushCallback<Boolean>) 
        
      LoadableInfoDialog(AlertDialog):  # dialog with loadable information 
        # create dialog with title as 'title' that loading until callback that is pushing in
        # 'callback' do not receive loaded string
        *LoadableInfoDialog(title: String or string-res, callback: PushCallback<PushCallback<String>>)
        
      InfoDialog(LoadableInfoDialog):  # dialog that shows information
        *InfoDialog(title: String, message: String) -> immidiately push 'message' in LoadableInfoDialog
        
      LoadingDialog:  # loading dialog
        *show(...) -> hide previous dialog and show new one on current activity with '...' args
        *hide() -> hide previous dialog
        *setTitle(title: String or string-res) -> set dialog title as 'title'
        
      (1): ArrayList<TransformedItem<String, String>>>
      StringsListEditor:  # dialog with strings list editor
        # show list editor with default values
        *show(title: String, strings: (1), callback: PushCallback<(1)>)
        
      CreateProjectDialog -> dialog with project creation
      EditProject -> dialog with project edition
      
    -callback  # callbacks storage
    
      ActivityLifecycleCallback(ActivityLifecycleCallbacks) -> actiity lifecycle callback with updating context in ContextUtils
      
      Mapper<FirstType, SecondType>:  # SecondType from FirstType converter
        *abstract map(item: FirstType) -> SecondType
        
      Predicate<Type>:  # check object of type Type
        *abstract test(object: Type) -> boolean
      
      PushCallback<Type>:  # callback to push object of type Type
        *onComplete(object: Type)

    -compiler  # package with compiler wrapper
    
      Compiler:  # com.vipapp.fjc.ApkBuilder wrapper
        *init() -> initialize Aapt and androidJar with ApkBuilder
        *needInit() -> boolean; check if androidJar already exists
        
        # call compileRelease('project', 'null', 'callback') and
        # compile without certificate (now AppMark uses only it)
        *compileDebug(project: Project, callback: ApkBuilderCallBack)
        
        # compile apk with ApkBuilder
        *compileRelease(project: Project, certificate: ApkBuilderCert, callback: ApkBuilderCallBack)
        
    -exception  # package with AppMark exceptions
    
      IncorrectAIFName(Exception):  # throws when .aif file name is incorrect
        IncorrectAIFName(name: String) -> name setup
        
    -holder  # directory with view holders
    
      ChooserHolder(ViewHolder) -> view holder in dialog chooser
      EmptyHolder(ViewHolder) -> empty view holder (using for create holders in runtime)
      FileHolder(ViewHolder) -> view holder in file manager
      ImageHolder(ViewHolder) -> view holder in gallery
      InsertSymbolHolder(ViewHolder) -> view holder in fast symbols in CodeActivity
      MainMenuHolder(ViewHolder) -> view holder in menu in MainActivity
      ProjectHolder(ViewHolder) -> view holder in projects list in MainActivity
      StringsHolder(ViewHolder) -> view holder in strings list in StringEditorActivity
      StringListEditorHolder(ViewHolder) -> view holder in StringsListEditorDialog
      
    -item
    
      -design
      
        -attribute -> directory with all xml attributes for design viewer
        
        DesignAttribute:  # support class to build attribute for view
          *DesignAttribute(name: String) -> setup name
          *apply(view: View, object: XMLObject, resources: Res) -> uses in DesignObject to apply attribute
          *abstract applyAction(view: View, resources: Res) -> uses in (? extends DesignAttribute) to apply attribute
            
        DesignObject:  # support class to build design
          
          > object: XMLObject  # source widget to build
          > availablePackages: String[]  # packages to inflate android view
          > availableArgs: Class[]  # args to apply attribute
          
          *getName() -> String  # get class name without package
          *getFullName() -> String  # get full class name
          
          *getView() -> View  # create instance of view
          
          *setupChildren() -> get children of XMLObject and add it to main view
          
          *applyInvokableAttributes(...) -> try to invoke set[attribute name] with 'availableArgs' args
          *applyAttributes(...) -> apply all attributes from Const.ATTRIBUTES and call applyInvokableAttributes(...)
          
          # get view with getView() and setup with applyAttributes() and if View is instance of ViewGroup
          # call setupChildren()
          *setupView(object: XMLObject, project: Project) -> View
      
      -setting  # directory with setting types and items (used in SettingMenu to build settings)
      
      Item<Type> -> simple item of class type Type and type 'type'
        *Item([type: int,] instance: Type) -> setup 'type' and 'instance'
      
      ActivityResult -> used in BaseActivity.addOnActivityResultCallback to push activity result
      OnLoadItem -> used in BaseActivity.addOnActivityResultCallback to push
      CallbackObject -> used in DefaultMenu to push items
      FileActionItem -> used in FileActionButton as onClick
      FileItem -> used in FileMenu to create top buttons ('goto...', 'create...', ...)
      FileLocale -> used in StringsEditorActivity to change locale
      OnProjectEdited -> used in EditProject to notify about project editing
      ProjectItem -> used in CreateProjectDialog to notify about project creating
      SettingsItem + SettingsType -> used to build settings
      SpanItem -> used to store spans
      StringDara -> used in CodeActivity to save text state
      TransformedItem<T1, T2> -> used to store 2 objects with types T1 and T2
      
      ClassItem -> Class wrapper
      Image -> Bitmap wrapper  
      Method -> method wrapper
      
    -manager  # directory with managers 
    
      -res -> DefaultResManagers 
      
      DefaultManager<Type>  # default manager (object that loads list of smth in thread)
        # array list with push callbacks to invoke when items loaded
        > callbacks: ArrayList<PushCallback<ArrayList<Type>>>
        # array list with items
        > objects: ArrayList<Type>
        # true while 'reload' method is executing
        > running: boolean
        
        # push 'objects' if not running or add 'callback' into 'callbacks'
        *exec(callback: PushCallback<ArrayList<Type>>)
        
        *execCallbacks() -> exec all callbacks from 'callbacks'
        
        # reload items, push them into 'callback' if it is and invoke execCallbacks()
        *reload([callback: PushCallback<ArrayList<Type>>,] args: Object...)
        
      DefaultResManager<T>(DefaultManager<XMLObject>)  # manager to load resource values for project
        > tagName: String  # name of tag to parse (e.g. "string", "color", ...)
        > xml_root_object: XMLObject  # root object for convertation to string
        > xml_objects_array: XMLArray  # array with parsed tags
        > file: File  # file to be parsed
        
        # setup values and call super(source) if source exists
        *DefaultResManager(source: File, tagName: tagName)
        # return new manager with locale 'locale'
        *getLocaled(project: Project, locale: String) -> DefaultResManager<T>
        # return all available locales
        *getLocales(project: Project) -> ArrayLisrt<FileLocale)
        # return xml object of resource value
        *getObject(name: String) -> XMLObject
        # return object of resource value by converting with toValue()
        *get(name: String) -> T
        
        # overations with resource values
        *add(...)
        *rename(...)
        *changeValue(...)
        *save(...)
        
        
        - Overridable -
        *abstract toValue(stringValue: String) -> T
        *abstract fromValue(value: T) -> String
        
      GalleryManager -> manager to load images (have a bug)
      ProjectManager -> manager to load projects
      
    -menu  # menus storage
    
      DefaultMenu<ListItemType, ViewHolderType(ViewHolder)>:  # support class to build lists
        > array: ArrayList<ListItemType>  # list to build
        > adapter: DefaultAdapter  # parent adapter
        
        *onAdapterReceived(adapter: DefaultAdapter) -> method to transfer parent adapter
        
        # push array to adapter and notify it if 'need_to_notify'
        *pushArray(arrayList: ArrayList<ListItemType>, need_to_notify = true)
        # push item to adapter
        *pushItem(item: Item)
        
        *notifyRemoved(...)  |
        *notifyChanged(...)  | -> notify adapter about changes
        *notifyInserted(...) |
        
        *getRecyclerView() -> return parent recycler if it has been pushed
        
        - Overridable -
        *abstract list(context: Context) -> ArrayList<ListItemType>  # return list with items to build
        *abstract bind(vh: ViewHolderType, item: ListItemType, i: int)  # setup view from item
        
        *onValueReceived(item: Item)  # method to transfer data between recycler and menu
        *getItemViewType(position: int) -> int; method to get item view type in DefaultAdapter
        # calls in case when attribute "app:holder" in RecyclerView is empty or getItemViewType
        *getViewHolder(parent: ViewGroup, itemType: int) -> ViewHolderType
        *size() -> int; return size of 'array' by default
        
      ChooserMenu(DefaultMenu) -> used in StringChooser picker
      EmptyMenu(DefaultMenu) -> used in RecyclerView when "app:menu" is empty
      FileMenu(DefaultMenu) -> used in file manager in CodeActivity
      ImageMenu(DefaultMenu) -> used in gallery menu
      InsertSymbolMenu(DefaultMenu) -> used in fast symbol in CodeActivity
      MainScreenMenu(DefaultMenu) -> used in MainActivity in main menu
      ProjectMenu(DefaultMenu) -> used in project list in main activity
      SettingsMenu(DefaultMenu) -> used in SettingsActivity to build settings
      StringsListEditorMenu(DefaultMenu) -> used in StringsListEditorDialog
      StringsMenu(DefaultMenu) -> used in strings list in StringsEditorActivity
      
    -picker  # pickers storage
    
      -string  # string pickers with additional check
        
        FileNamePicker -> file name picker
        LocalePicker -> locale name picker
      
      DefaultPicker<Type>  # class to pick values of type Type
        > callbacks: ArrayList<PushCallback<Type>>  # list with callbacks
        
        *DefaultPicker(callback: PushCallback<Type>) -> add 'callback' to 'callbacks'3
        *addCallback(callback: PushCallback<Type>) -> add 'callback' to 'callbacks'        
        *pushItem(item: Type) -> exec all callbacks with 'item'
        
      SimpleIntPicker(DefaultPicker<Integer>) -> int picker with NumberPicker from 'min' to 'max'
              *SimpleIntPicker(..., min: int, max: int)
        
      ImagePicker(DefaultPicker<Image>) -> gallery image picker
      ProjectPicker(DefaultPicker<ProjectItem>) -> ProjectItem picker
      ReplacePicker(DefaultPicker<TransformedItem<String, String>>) -> regexp to replace and replace string picker
      StringChooser(DefaultPicker<Item<String>>) -> string from list picker
      StringPicker(DefaultPicker<String>) -> string from keyboard picker
      
    -project 
    
      AIF(ThreadLoader):  # AppMark info file (file with service information)
        > AIF_VERSION = 7  # Newest aif version (used to update it)
         
        > PACKAGE            |
        > APP_VERSION        | -> key consts
        > APP_VERSION_NUMBER |
        > VERSION            |
      
        > aif: File  # source file
        > info: HashMap<String, String>  # parsed data
        > project: Project  # parent project
        
        *AIF(...)  # call load(...) with args
        
        *isAIF(pathname: String) -> boolean  # pathname matches aif regexp
        *onAttachProject(project: Project) -> method to attach project
        
        *readAif() -> HashMap<String, String>  # parse 'source' file
        *writeAif(thread = true)  # write 'info' to 'source' (in thread if 'thread')
        
        *save(thread = true)  # call writeAif('thread')
        
        {
          AIF UPDATES - support methods to update aif
        }
        
        *updateAif(old_version: int)  # update aif from 'old_version' to 'AIF_VERSION'
        
        # unknown recursion magic code 
        *load(...)  # create aif if first arg is HashMap or read already existin
        
      AndroidManifest(CallableThreadLoader):  # manifest parser
        > source: File  # source manifest file
        
        > parsed_manifest: XMLObject  # parsed xml object of 'source' file
        > parsed_application: XMLObject  # parsed <application> tag to manage activities
        
        > text: String  # text content of 'source' file
        
        *private AndroidManifest(File file)  # setup 'source'
        *fromFile(File file) -> AndroidManifest  # create instance of AndroidManifest from file
        
        *attachProject(Project project)  # attach project to manifest
        
        # generate new manifest in current thread
        *generateNewUI(project_package: String, app_name: String, version_name: String, version_id: int, first_activityz: String, minSDK: int)
        
        *parse()  # setup 'parsed_manifest' and 'parsed_application' from 'text'
        
        *load(args: Object...)  # read text from 'source' and parse()
        
        *isCorrect() -> boolean  # return true if parsed successfully
        
        *saveUI()  # save manifest in current thread
        
        {
          get... and set... methods with 'parsed_manifest'
        }
        
      Drawables:  # class to search drawables
        *Drawables(source: File)  # setup source
        *getDrawable(name: String) -> File  # find drawable file in 'source'
        
      Project(ThreadLoader, Serializable)  # support class for project
        > resources: Res  # resources support class
        > source: File  # project source file
        > aif: AIF  # info file class
        > manifest: AndroidManifest  # android manifest support class
        > supported: boolean  # result of project setup
        > settings: ProjectSettings  # parsed settings from aif file
      
        *private Project(file: File)  # call super(file)
        *fromFile(file: File) -> Project  # create new instance of project
        
        {
          setup methods
        }
        
        *setup()  # setup methods executor
        
        # try-catch wrapper of setup() method
        *try_to_setup() -> boolean  # return was exception throwed while executing setup() or not
        
        *load(args: Object...)  # call try_to_setup() and store result in 'supported'
        
        *saveUI()  # save project in current thread
        *save([onSave: PushCallback<Void>])  # save project in new thread and exec 'onSave' callback if it exists
        
        *saveSettings()  # save project settings
        
        *delete()  # delete 'source' file
        
        *localizeString(string: String)  # translate 'string' to current locale
        
        {
          getters and setters
        }
        
        *notProject(file: File) -> boolean  # return false if 'file' is not directory or is not contains exactly 1 .aif file
        
        *createNew(...)  # create new project
        *generateAIF(...)  # generate new aif
        
      Res(ThreadLoader):  # support class to load project resources
        > androidRes: Resources  # android system resources
        
        > strings: StringsManager |
        > colors: ColorsManager   | -> managers
        > drawables: Drawables    |
        
        > source: File  # source 'res' directory
        
        *private Res(file: File)
        *fromFile(file: File) -> Res  # create instance of Res
        
        {
          setup methods
        }
        
        *load(args: Object...)  # call setup methods
        
        {
          resource getters
        }
        
        # get system resource by 'name' and convert it to type T
        *<T> getAndroidResource(name: String) -> T  
        
        # get resource by 'name' with resource getters and convert it to type T
        *<T> get(name: String) -> T
        
        
      ProjectSettings -> class to store file pathes to build project
      DefaultProjectSettings(ProjectSettings) -> class to create default settings for project
        