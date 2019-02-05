
cmake_minimum_required(VERSION 3.4.1)

#将全部cpp文件(注意：不包含.h文件)的绝对路径赋值给变量
#其实file()方法的作用就是将多个cpp文件绝对路径统一管理起来，不然add_library（）方法的参数三就得写全路径
file(GLOB player src/main/cpp/player/*.cpp)


#参数一：变量名，表示源文件生成.so/.a文件的名字
#参数二：SHARED：表示生成动态库；STATIC：表示生成静态库
#参数三：源文件路径
add_library(native-lib SHARED src/main/cpp/native-lib.cpp)
add_library(player-lib SHARED ${player})




#扫描ffmpeg的所有头文件（头文件类似于Java的接口，库文件里是实现）
include_directories(src/main/cpp/include)

#set（）方法用作获取动态/静态库文件，相当于将-L${CMAKE_SOURCE_DIR}/src/main/cpp/libs/${ANDROID_ABI}赋给CMAKE_CXX_FLAGS
#CMAKE_CXX_FLAGS：只要有C++就用这个
#-L：库的查找路径（联想--sysroot~~)
#CMAKE_SOURCE_DIR：当前CMakeLists.txt路径

#扫描ffmpeg所有库文件
#ANDROID_ABI：Android cmake内置变量，可根据当前的CPU架构获取不同的.a/.so文件
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -D__STDC_CONSTANT_MACROS -L${CMAKE_SOURCE_DIR}/src/main/cpp/libs/${ANDROID_ABI}")



#liblog省略lib
# avformat avcodec avfilter avutil swresample swscale有顺序讲究，被依赖的库先加载
target_link_libraries(

        player-lib
        native-lib

        avformat avcodec avfilter avutil swresample swscale
        log
        z
        android
        OpenSLES)