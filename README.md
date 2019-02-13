# PlayerDemo
ffmpeg实践项目--音视频播放器
该项目是一个基于ffmpeg开发的音视频播放器，运用ffmpeg按“解协议->解封装->解码->音视频同步->屏幕渲染”流程一步步实现。首先，本项目涉及多线程开发，
故所以线程同步控制尤为重要，如对安全队列SafeQueue的封装；其次是音视频同步参考系的选取，本项目暂时以音频相对时间戳为参考系，原因是调整视频不易引
起用户察觉，也简单。最后强调C++编程规范，杜绝野指针、对象以及线程的回收，避免内存泄漏。本项目实现的前提是必须理解音视频基础，参考资料有雷霄骅博客，
慕课网李超视频教程，何俊林《Android音视频开发》等。
