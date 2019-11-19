# appmark
Mobile Android IDE

This is android app where you can build your own apps using Java

support: https://unikalni4elovek.000webhostapp.com/donate

# struct:
SOURCE DIRECTORY: appmark/app/src/main/java/com/vipapp/appmark2 <br>

    -activities  # activity storage 
      
      /**
      *    ACTIVITY ARCHITECTURE:
      *  onCreate(...) - setContentView(...) call
      *  findViews() - findViewById(...) calls
      *  setCallnacks() - set all callbacks
      *  setupVars() - set default values for variables
      *  setupViews() - set default state for views
      **/
      
      BaseActivity:  # main activity class 

        > callbacks: ArrayList<PushCallback<ActivityResult>>  # list with all onActivityResult callbacks
        > onLoadCallbacks: ArrayList<PushCallback<OnLoadItem>>  # list with all onItemLoad callback 
        
        *exit_with_permission_error() -> exit from app and show permission error
        *onLoad(item: OnLoadItem) -> call all 'onLoadCallback' and onLoadCallback(...)
        
        *addOnActivityResultCallback(callback: ...) -> add callback to 'callbacks'
        *addOnLoadCallback(callback: ...) -> add callback to 'onLoadCallback'
        
        - Overridable -
        *onLoadCallback(item: OnLoadItem) in onLoad(...)
        *findViews() in onCreate(...)
        *setCallbacks() in onCreate(...)
        *setupVars() in onCreate(...)
        *setupViews() in onCreate(...)
        
        - Overriden -
        *onCreate(...) -> set default orientation and call architecture methods
        *onRequestPermissionResult(...) -> exit from app if any permission rejected or recreate activity
        *onActivityResult(...) -> call all 'callbacks'
        
      IntroActivity(BaseActivity) -> activity with introduce animation and permission requiesting
        ...
        *initCompilier(...) -> init compilier if need
        ...
        
      DebugActivity(BaseActivity) -> activity with error view
        
