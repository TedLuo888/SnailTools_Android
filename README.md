# SnailTools_Android

本项目包含Android项目开发中常用的工具类，包括文件操作相关的FileTools,资源相关的ResourcesTools，设备相关的DeviceTools，字符串相关的StringTools等



# 一、StringTools，字符串工具类

   该类包含String处理相关的方法，其他通用的String方法在一下其他库中已经有了，或者在Android的源码中也有提供相关String相关的工具类，所有这里只提供了

   两个常用但是其他地方没有的方法：



* 1、public static int lengthSafely(CharSequence str)，可以安全地返回字符串的长度，如果str为空返回0

* 2、public static boolean isEmpty(CharSequence str)，判断字符串是否为空，字符串为空表示字符串为null或者内容为空

* 3、public static boolean isBlank(CharSequence str)，判断字符串是否只有空格

其他相关方法请参考StringTools.java



# 二、TimeTools，时间处理工具类

 该类主要用于格式化时间格式，可以使用代码中提供的格式，也可以自定义格式

    

* public static String getTime(long timeInMillis, SimpleDateFormat dateFormat)，其中有提供几种常见的格式



具体请参考TimeTools.java



# 三、SDKTools，用于对sdk版本号进行判断的工具类

 例如可以使用public static boolean isSdkLargeLOLLIPOP()方法判断当前是否是Android5.0及以后的版本

 

# 四、ResourceTools，资源管理工具类

  提供一系列方法用于通过资源名称获取资源，例如：

```

  public static boolean getBoolByName(String resName, Context context)

  

```

  其他通过资源名称获取资源的方法请参考ResourceTools.java

  

  另外还提供了一系列从主题属性中获取资源的方法，例如：

  

```

 public static int getAttrColor(Context context, int attrRes)  



```

  

  其他细节请参考ResourceTools.java

  

# 五、PackageTools

 该类主要提供了获取application版本信息和application名称的方法

     

# 六、NumberTools

 该类主要提供处理float或者double小数点保留几位的方法，例如如果需要对一个float数据123.556进行处理，并保存小数点后2位，可以使用

  public static double format(float input,int decimalPlaces)来实现

      

# 七、InputMethodTools

 该类主要用于显示和隐藏输入法，并可以设置监听，在输入法显示和隐藏的时候处理相关业务逻辑

   

# 八、FileTools

 

   该类包含了常用的文件操作，例如：copy,delete,mkdir等，同时提供了获取文件大小并转换为可读大小的方法



# 九、DrawableTools

 

   该类主要提供了drawable和bitmap之间相互转换的方法，同时还提供了从一个View创建bitmap的方法

   

# 十、DeviceTools

 

   该类主要用于获取设备信息，例如IMEI等，同时提供了获取手机build.prop中的数据的方法，例如：

   

```

   public static String getStringFromBuildProp(String key,String defaultValue)

   

```



# 十一、DensityTools

 

   该类主要提供获取手机一下常用的View的大小以及屏幕大小的方法，例如获取ActionBar高度，StatusBar高度，屏幕宽高等。同时还提供了dp和px之间相互转换的方法

 

# 十二、ColorTools

 

   该类不同于其他项目中对于颜色的处理，它主要功能是可以通过一张背景图实现背景图上面文本颜色的自适配

   

```

   public static int getReverseTextColorFromBitmap(Bitmap backgroudBitmap)

   

```

   通过该方法可以根据Bitmap图片将文本颜色变黑或者变白

   

# 十三、BitmapTools

  提供Bitmap的缩放，剪切，旋转，占用内存大小，计算采样率等方法，其中项目中最常用的莫过于计算采样率：

    

```

     public static int calculateBitmapInSampleSize(

            BitmapFactory.Options options, int reqWidth, int reqHeight)

    

```

    

# 十四、ArrayTools

  该类目前只有判断集合和数组是否为空的接口

   

