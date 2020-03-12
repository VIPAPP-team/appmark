package com.vipapp.appmark2.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.EditViewDialogItem;
import com.vipapp.appmark2.item.ImageItem;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.item.SettingsItem;
import com.vipapp.appmark2.item.design.DesignAttribute;
import com.vipapp.appmark2.item.design.attribute.BackgroundAttribute;
import com.vipapp.appmark2.item.design.attribute.GravityAttribute;
import com.vipapp.appmark2.item.design.attribute.HeightAttribute;
import com.vipapp.appmark2.item.design.attribute.HintColorAttribute;
import com.vipapp.appmark2.item.design.attribute.IdAttribute;
import com.vipapp.appmark2.item.design.attribute.OrientationAttribute;
import com.vipapp.appmark2.item.design.attribute.ScaleTypeAttribute;
import com.vipapp.appmark2.item.design.attribute.SrcAttribute;
import com.vipapp.appmark2.item.design.attribute.TextColorAttribute;
import com.vipapp.appmark2.item.design.attribute.TextSizeAttribute;
import com.vipapp.appmark2.item.design.attribute.TextStyleAttribute;
import com.vipapp.appmark2.item.design.attribute.VisibilityAttribute;
import com.vipapp.appmark2.item.design.attribute.WidthAttribute;
import com.vipapp.appmark2.item.design.attribute.margin.MarginAttribute;
import com.vipapp.appmark2.item.design.attribute.margin.MarginBottom;
import com.vipapp.appmark2.item.design.attribute.margin.MarginEnd;
import com.vipapp.appmark2.item.design.attribute.margin.MarginHorizontal;
import com.vipapp.appmark2.item.design.attribute.margin.MarginLeft;
import com.vipapp.appmark2.item.design.attribute.margin.MarginRight;
import com.vipapp.appmark2.item.design.attribute.margin.MarginStart;
import com.vipapp.appmark2.item.design.attribute.margin.MarginTop;
import com.vipapp.appmark2.item.design.attribute.margin.MarginVertical;
import com.vipapp.appmark2.item.design.attribute.padding.PaddingAttribute;
import com.vipapp.appmark2.item.design.attribute.padding.PaddingBottom;
import com.vipapp.appmark2.item.design.attribute.padding.PaddingEnd;
import com.vipapp.appmark2.item.design.attribute.padding.PaddingHorizontal;
import com.vipapp.appmark2.item.design.attribute.padding.PaddingLeft;
import com.vipapp.appmark2.item.design.attribute.padding.PaddingRight;
import com.vipapp.appmark2.item.design.attribute.padding.PaddingStart;
import com.vipapp.appmark2.item.design.attribute.padding.PaddingTop;
import com.vipapp.appmark2.item.design.attribute.padding.PaddingVertical;
import com.vipapp.appmark2.item.editviewdialogitem.choose.ProgressBarStyle;
import com.vipapp.appmark2.item.editviewdialogitem.choose.ScaleType;
import com.vipapp.appmark2.item.editviewdialogitem.choose.bool.Checked;
import com.vipapp.appmark2.item.editviewdialogitem.choose.bool.Indeterminate;
import com.vipapp.appmark2.item.editviewdialogitem.color.HintColor;
import com.vipapp.appmark2.item.editviewdialogitem.color.TextColor;
import com.vipapp.appmark2.item.editviewdialogitem.choose.TextStyle;
import com.vipapp.appmark2.item.editviewdialogitem.choose.bool.SingleLine;
import com.vipapp.appmark2.item.editviewdialogitem.drawable.Src;
import com.vipapp.appmark2.item.editviewdialogitem.multiplechoose.Gravity;
import com.vipapp.appmark2.item.editviewdialogitem.choose.bool.Enabled;
import com.vipapp.appmark2.item.editviewdialogitem.multiplechoose.ImeOption;
import com.vipapp.appmark2.item.editviewdialogitem.multiplechoose.InputType;
import com.vipapp.appmark2.item.editviewdialogitem.multiplechoose.LayoutGravity;
import com.vipapp.appmark2.item.editviewdialogitem.string.Hint;
import com.vipapp.appmark2.item.editviewdialogitem.unit.no.Alpha;
import com.vipapp.appmark2.item.editviewdialogitem.unit.no.Lines;
import com.vipapp.appmark2.item.editviewdialogitem.unit.no.Max;
import com.vipapp.appmark2.item.editviewdialogitem.unit.no.Progress;
import com.vipapp.appmark2.item.editviewdialogitem.unit.no.Rotation;
import com.vipapp.appmark2.item.editviewdialogitem.unit.no.ScaleX;
import com.vipapp.appmark2.item.editviewdialogitem.unit.no.ScaleY;
import com.vipapp.appmark2.item.editviewdialogitem.unit.sp.TextSize;
import com.vipapp.appmark2.item.editviewdialogitem.unit.dp.TranslationX;
import com.vipapp.appmark2.item.editviewdialogitem.unit.dp.TranslationY;
import com.vipapp.appmark2.item.editviewdialogitem.unit.no.Weight;
import com.vipapp.appmark2.item.editviewdialogitem.border.Margin;
import com.vipapp.appmark2.item.editviewdialogitem.border.Padding;
import com.vipapp.appmark2.item.editviewdialogitem.choose.Orientation;
import com.vipapp.appmark2.item.editviewdialogitem.choose.Visibility;
import com.vipapp.appmark2.item.editviewdialogitem.drawableOrColor.Background;
import com.vipapp.appmark2.item.editviewdialogitem.unit.no.WeightSum;
import com.vipapp.appmark2.item.editviewdialogitem.string.Id;
import com.vipapp.appmark2.item.editviewdialogitem.size.LayoutHeight;
import com.vipapp.appmark2.item.editviewdialogitem.size.LayoutWidth;
import com.vipapp.appmark2.item.editviewdialogitem.string.Text;
import com.vipapp.appmark2.item.setting.item.AppVersionName;
import com.vipapp.appmark2.item.setting.item.DebuggerEnabled;
import com.vipapp.appmark2.item.setting.item.LabelItem;
import com.vipapp.appmark2.item.setting.item.MainEditorFontSize;
import com.vipapp.appmark2.item.setting.item.MaxLinesInEditor;
import com.vipapp.appmark2.item.setting.item.OpenLastProject;
import com.vipapp.appmark2.item.setting.item.ServerStatus;
import com.vipapp.appmark2.item.setting.item.SettingTitleItem;
import com.vipapp.appmark2.item.setting.item.ShowDesignError;
import com.vipapp.appmark2.item.setting.item.ShowHighlightUpdate;
import com.vipapp.appmark2.item.setting.item.UndoOnBackPressed;
import com.vipapp.appmark2.item.widget.Button;
import com.vipapp.appmark2.item.widget.CalendarView;
import com.vipapp.appmark2.item.widget.CheckBox;
import com.vipapp.appmark2.item.widget.EditText;
import com.vipapp.appmark2.item.widget.ImageView;
import com.vipapp.appmark2.item.widget.LinearLayout;
import com.vipapp.appmark2.item.widget.ListView;
import com.vipapp.appmark2.item.widget.ProgressBar;
import com.vipapp.appmark2.item.widget.ScrollView;
import com.vipapp.appmark2.item.widget.SeekBar;
import com.vipapp.appmark2.item.widget.Switch;
import com.vipapp.appmark2.item.widget.TextView;
import com.vipapp.appmark2.item.widget.View;
import com.vipapp.appmark2.item.widget.WebView;
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

@SuppressWarnings("WeakerAccess")
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
    private static final String SERVER_VIPAPP = "https://vipapp.site/api/appmark/";

    private static final String SERVER_URL = SERVER_VIPAPP;
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
    public static final String SHOW_DESIGN_ERROR_PREF = "showDesignError";

    public static final String DEFAULT_APP_NAME = "MyApp";
    public static final String DEFAULT_PACKAGE_NAME = "com.mycompany.myapp";
    public static final String DEFAULT_VERSION_NAME = "1.0";
    public static final String DEFAULT_VERSION_CODE = "1";

    // SETTINGS DEFAULT
    public static final boolean DEFAULT_HIGHLIGHT_DEBUG = false;
    public static final boolean DEFAULT_ON_BACK_PRESSED = false;
    public static final boolean DEFAULT_DEBUGGER_ENABLED = true;
    public static final boolean DEFAULT_OPEN_LAST_PROJECT = true;
    public static final boolean DEFAULT_SHOW_DESIGN_ERROR = false;
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
    public static boolean SHOW_DESIGN_ERROR;
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
    public static final String LAYOUT_GRAVITY_ATTR = "android:layout_gravity";
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
    public static final String TEXT_SIZE_ATTR = "android:textSize";
    public static final String TEXT_STYLE_ATTR = "android:textStyle";
    public static final String HINT_COLOR_ATTR = "android:textColorHint";
    public static final String SCALE_TYPE_ATTR = "android:scaleType";
    public static final String STYLE_ATTR = "android:style";

    public static final ArrayList<ImageItem> WIDGETS = new ArrayList<ImageItem>(){{
        add(new View());
        add(new LinearLayout());
        add(new ScrollView());
        add(new TextView());
        add(new EditText());
        add(new Button());
        add(new ImageView());
        add(new CheckBox());
        add(new ListView());
        add(new WebView());
        add(new Switch());
        add(new SeekBar());
        add(new ProgressBar());
        add(new CalendarView());
    }};

    public static final ArrayList<EditViewDialogItem> EDIT_DIALOG_ATTRIBUTES = new ArrayList<EditViewDialogItem>(){{
        add(new Id());
        add(new LayoutWidth());
        add(new LayoutHeight());
        add(new Padding());
        add(new Margin());
        add(new Background());
        add(new Orientation());
        add(new Gravity());
        add(new LayoutGravity());
        add(new Src());
        add(new ScaleType());
        add(new Checked());
        add(new Max());
        add(new Progress());
        add(new Indeterminate());
        add(new ProgressBarStyle());
        add(new Text());
        add(new TextSize());
        add(new TextColor());
        add(new TextStyle());
        add(new SingleLine());
        add(new Lines());
        add(new Hint());
        add(new HintColor());
        add(new InputType());
        add(new ImeOption());
        add(new Visibility());
        add(new Enabled());
        add(new WeightSum());
        add(new Weight());
        add(new Alpha());
        add(new Rotation());
        add(new TranslationX());
        add(new TranslationY());
        add(new ScaleX());
        add(new ScaleY());
    }};

    public static final ArrayList<DesignAttribute> ATTRIBUTES = new ArrayList<DesignAttribute>(){{
        add(new BackgroundAttribute());
        add(new MarginAttribute());
        add(new MarginLeft());
        add(new MarginRight());
        add(new MarginBottom());
        add(new MarginTop());
        add(new MarginStart());
        add(new MarginEnd());
        add(new MarginHorizontal());
        add(new MarginVertical());
        add(new PaddingAttribute());
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
        add(new TextSizeAttribute());
        add(new TextStyleAttribute());
        add(new HintColorAttribute());
        add(new ScaleTypeAttribute());
    }};

    public static final ArrayList<String> ime_option_choose = new ArrayList<String>(){{
        add("actionDone");
        add("actionSend");
        add("actionNext");
        add("actionPrevious");
        add("actionGo");
        add("actionSearch");
        add("actionName");
        add("actionUnspecified");
        add("flagNoPersonalizedLearning");
        add("flagNoExtractUi");
        add("flagNavigatePrevious");
        add("flagNavigateNext");
        add("flagNoEnterAction");
        add("flagNoAccessoryAction");
        add("flagNoFullscreen");
        add("flagForceAscii");
    }};

    public static final ArrayList<String> input_type_choose = new ArrayList<String>(){{
        add("date");
        add("textUri");
        add("textShortMessage");
        add("textLongMessage");
        add("textAutoCorrect");
        add("numberSigned");
        add("textVisiblePassword");
        add("textWebEditText");
        add("textMultiLine");
        add("textNoSuggestions");
        add("textFilter");
        add("number");
        add("datetime");
        add("textWebEmailAddress");
        add("textPersonName");
        add("text");
        add("textPhonetic");
        add("textCapSentences");
        add("textPassword");
        add("textAutoComplete");
        add("textImeMultiLine");
        add("textPostalAddress");
        add("numberDecimal");
        add("textEmailAddress");
        add("numberPassword");
        add("textCapWords");
        add("phone");
        add("textEmailSubject");
        add("textCapCharacters");
        add("time");
        add("textWebPassword");
    }};

    public static final ArrayList<String> gravity_chooser = new ArrayList<String>(){{
        add("center");
        add("center_horizontal");
        add("center_vertical");
        add("top");
        add("bottom");
        add("left");
        add("right");
        add("start");
        add("end");
        add("fill");
        add("fill_horizontal");
        add("fill_vertical");
        add("clip_horizontal");
        add("clip_vertical");
    }};

    public static final ArrayList<Item<String>> progress_bar_style_choose = new ArrayList<Item<String>>(){{
        add(new Item<>("?android:progressBarStyle"));
        add(new Item<>("?android:progressBarStyleHorizontal"));
    }};
    public static final ArrayList<Item<String>> scale_type_choose = new ArrayList<Item<String>>(){{
        add(new Item<>("centerInside"));
        add(new Item<>("fitStart"));
        add(new Item<>("fitEnd"));
        add(new Item<>("center"));
        add(new Item<>("matrix"));
        add(new Item<>("fitXY"));
        add(new Item<>("fitCenter"));
        add(new Item<>("centerCrop"));
    }};

    public static final ArrayList<Item<String>> add_choose = new ArrayList<Item<String>>(){{
        add(new Item<>(0, Str.get(R.string.add_before)));
        add(new Item<>(1, Str.get(R.string.add_inside)));
    }};

    public static final ArrayList<Item<String>> text_style_chooser = new ArrayList<Item<String>>(){{
        add(new Item<>("bold"));
        add(new Item<>("italic"));
        add(new Item<>("bold|italic"));
    }};

    public static final ArrayList<Item<String>> orientation_chooser = new ArrayList<Item<String>>(){{
        add(new Item<>("horizontal"));
        add(new Item<>("vertical"));
    }};

    public static final ArrayList<Item<String>> boolean_chooser = new ArrayList<Item<String>>(){{
        add(new Item<>("true"));
        add(new Item<>("false"));
    }};

    public static final ArrayList<Item<String>> visibility_chooser = new ArrayList<Item<String>>(){{
        add(new Item<>("visible"));
        add(new Item<>("invisible"));
        add(new Item<>("gone"));
    }};

    public static final ArrayList<Item<String>> drawables_chooser = new ArrayList<Item<String>>(){{
        add(new Item<>(0, Str.get(R.string.drawable)));
        add(new Item<>(1, Str.get(R.string.color)));
        add(new Item<>(2, Str.get(R.string.other)));
    }};

    public static final ArrayList<Item<String>> size_chooser = new ArrayList<Item<String>>(){{
        add(new Item<>(0, "match_parent"));
        add(new Item<>(1, "wrap_content"));
        add(new Item<>(2, Str.get(R.string.other)));
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
    public static final String REFERENCE_REGEX = "(\\?|@).+/";
    public static final String LOCALE_REGEX = "[a-z\\-]*";
    public static final String WORD_SPLITTER = "([^.\\w]|\\W(?=\\W))";
    public static final String IMPORTS_REGEX = "(?:package(.|\\n)*?(?=import)|.+\\{(?:.|\\s)*|import.*\\.|;)";
    public static final String VALUES_REGEX = "values(-[a-z]+)?";
    public static final String PACKAGE_REGEX = "^[a-z][a-z0-9_]*(\\.[a-z0-9_]+)+";
    public static final String IMAGE_REGEX = ".+\\.(png|jpg)";
    public static final String TEXT_FILE_REGEX = ".+\\.(xml|aif|java|json)";
    public static final String FILENAME_REGEX = "([a-zA-Z0-9\\s_\\\\.\\-\\(\\):])+";
    public static final String NAMED_DRAWABLE_REGEX = "%1$s\\.(png|jpg|xml)";
    public static final String DRAWABLE_REGEX = ".+\\.(png|jpg|xml)";
    public static final String NUMBER_REGEX = "-?[0-9.]+";
    public static final String SIZE_POSTFIX_REGEX = "(dp|sp|dip)";
    public static final String SIZE_REGEX = NUMBER_REGEX + SIZE_POSTFIX_REGEX + "?";
    public static final String SPACE_XML_NEWLINE = " (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

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
            add(new ShowDesignError());
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
        SHOW_DESIGN_ERROR = mSharedPreferences.getBoolean(SHOW_DESIGN_ERROR_PREF, DEFAULT_SHOW_DESIGN_ERROR);
    }

    public static String getDefaultLastFile(String project_package){
        return String.format(DEFAULT_FILE_DEFAULT_VALUE, project_package.replace('.', '/'));
    }

    public static String getJavaDir(String project_package){
        return String.format(JAVA, project_package.replace('.', '/'));
    }

}
