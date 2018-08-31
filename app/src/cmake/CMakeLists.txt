#cmake脚本


# 指定cmake最小支持版本的方法 VERSION 3.8
cmake_minimum_required(VERSION 3.6);


#hello-jni:变量名，最终生成的so名称
#SHARED 动态库，STATIC：静态库
add_library(hello-jni
SHARED
hello-jni.c)


#引入静态库或者动态库


#==========================================================================
#引入静态库
#Test 静态库的名称(libTest.a)  STATIC：标识为静态库  IMPORTED：表示这一个静态库是以导入的形式添加进来(预编译静态库)
add_library(Test STATIC IMPORTED)
#怎么导入?设置导入的路径
#设置Test的导入路径 属性
#${CMAKE_SOURCE_DIR}/src/cmake/libTest.a:当前CMakeLists.txt路径
set_target_properties(Test PROPERTIES IMPORTED_LOCATION libTest.a)

#==========================================================================

#引入动态库
#这边会遇到跟在Android.mk中遇到的同一个问题：找不到需要链接的动态库
#这边的libTest.so动态库一定要放到jniLibs中才会被打包到so中，否则会找不到这个动态库
add_library(Test SHARED IMPORTED)
set_target_properties(Test PROPERTIES IMPORTED_LOCATION libTest.a)


#这边有其他的办法能引入动态库，能解决这个问题
#set 方法 定义一个变量：CMAKE_C_FLAGS = ${CMAKE_C_FLAGS} XXX
#CMAKE_C_FLAGS c中的
#-L 库的查找路径 去这个路径下找到libTest.so
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -LXXX -LXXX -L${CMAKE_SOURCE_DIR}/src/cmake/libTest.so")




#去查找 log这个库，如果找到了就会把liblog.so的绝对路径赋值给 log-lib
#从哪里去找呢？？
#去NDK下的platform中去找库
find_library(log-lib log)
message("log-lib的路径：${lob-lib}")

#==========================================================================
#输出日志的方法
message("CMAKE_SOURCE_DIR：${CMAKE_SOURCE_DIR}")
#CPU的架构，做多平台的时候需要打包编译出多个平台的so
message("ANDROID_ABI：${ANDROID_ABI}")



#将libTest.a链接到libhello-jni.so
#hello-jni：要生成的so，Test：需要依赖的a
target_link_libraries(hello-jni Test ${log-lib})