# appmark
Mobile Android IDE

This is android app where you can build your own apps using Java

support: https://unikalni4elovek.000webhostapp.com/donate

# struct:
SOURCE DIRECTORY: appmark/app/src/main/java/com/vipapp/appmark2 <br>

    -activities  # activity storage 
      
      /**
      *    ACTIVITY ARCHITECTURE:
      *  onCreateView() -> int - return argument that will be passed in setContentView()
      *  createView() - additional method to set view
      *  findViews() - findViewById(...) calls
      *  setCallnacks() - set all callbacks
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
        
      IntroActivity(BaseActivity) -> activity with introduce animation and permission requiesting
        ...
        *initCompilier(...) -> init compilier if need
        ...
        
      DebugActivity(BaseActivity) -> activity with error view
        
