支持airplay音乐播放

      DroidAirplay中所有功能均在Android层实现，未使用lib库在本地处理。大概功能实现：
        1.使用JmDNS进行服务发现。其中AirTunes服务类型设置为”_raop._tcp.local.”， AirPlay服务类型设置为“_airplay._tcp.local.”。
        2.使用netty来发布服务，分别用来处理上述两种服务对应的请求处理。其中，AirTunes，AirPlay会指定不同的端口号，我们在netty发布服务时分别绑定对应的端口号。
        3.音频方面：从IOS设备设备传送过来的音频数据是经过加密的ALAC数据（ALAC 即Apple lossless audio codec的缩写，是苹果公司开发的一种无损音频格式），经过解密，我们可以得到原始的ALAC数据，但是该格式的数据在android设备上无法直接播放，因此，需要将alac格式转码成PCM格式，此格式的数据是原始的未经压缩的音频数据，可以直接播放,但是DroidAirplay在播放时，只能听到吱吱的杂音，并没有音乐输出。后来调试发现，源代码中，处理的音频采样数据是16位双声道的，在解决数据大小端问题时并不完善，需要再次更改才行。同时因为DroidAirPlay是基于AirReceiver的，后者利用java sound中的部分api，但是Android中用android.media.AudioTrack替代了java sound的接口，导致DroidAirPlay在处理时有些小问题，当然在使用DroidAirplay时将这些地方改过来就行了。
        4.视频图片方面：AirPlay服务这边相对比较简单，协议相对单一，分别对应请求时去做相应处理就行了。
        
      地址：http://blog.csdn.net/ville_zeng/article/details/19159439
