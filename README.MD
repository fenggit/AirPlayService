
      DroidAirplay中所有功能均在Android层实现，未使用lib库在本地处理。大概功能实现：
      
        1.使用JmDNS进行服务发现。其中AirTunes服务类型设置为”_raop._tcp.local.”， 
          AirPlay服务类型设置为“_airplay._tcp.local.”。
          
        2.使用netty来发布服务，分别用来处理上述两种服务对应的请求处理。
          其中，AirTunes，AirPlay会指定不同的端口号，我们在netty发布服务时分别绑定对应的端口号。
          
        3.音频方面：从IOS设备设备传送过来的音频数据是经过加密的ALAC数据（ALAC 即Apple lossless audio codec的缩写，
          是苹果公司开发的一种无损音频格式），经过解密，我们可以得到原始的ALAC数据，
          但是该格式的数据在android设备上无法直接播放，因此，需要将alac格式转码成PCM格式，
          此格式的数据是原始的未经压缩的音频数据，可以直接播放,但是DroidAirplay在播放时，
          只能听到吱吱的杂音，并没有音乐输出。后来调试发现，源代码中，处理的音频采样数据是16位双声道的，
          在解决数据大小端问题时并不完善，需要再次更改才行。同时因为DroidAirPlay是基于AirReceiver的，
          后者利用java sound中的部分api，但是Android中用android.media.AudioTrack替代了java sound的接口，
          导致DroidAirPlay在处理时有些小问题，当然在使用DroidAirplay时将这些地方改过来就行了。
          
        4.视频图片方面：AirPlay服务这边相对比较简单，协议相对单一，分别对应请求时去做相应处理就行了。
        
       地址：http://blog.csdn.net/ville_zeng/article/details/19159439
       
       
       
      
      
AirPlay协议小结   

AirPlay协议是苹果公司实现的在苹果产品之间传输媒体流信息的一组协议。苹果公司产品之间可以使用该技术自动地互相发现，并且轻松地互相传输音乐、图片及视频文件。此外，AirPlay还有一种镜像（AirPlay Mirroring）功能，可以将iPad或者iPhone的整个屏幕投放到更大屏幕的电视机上，实现“无线投屏”的效果。
AirPlay协议的基础是组播DNS（Multicast Domain Name Server，简称mDNS）协议和DNS服务发现（DNS Service Discovery，简称DNS-SD）协议，它们是IETF Zeroconf工作组（www.zeroconf.org）提出的用于自动寻找设备及服务的网络协议，苹果公司以这两个协议为基础，实现了苹果公司数字家庭网络框架。
AirPlay协议消息发送格式及规则基于mDNS协议（http://files.multicastdns.org/draft-cheshire-dnsext-multicastdns.txt），mDNS协议基于组播技术，定义了家庭各个设备之间的消息的基本格式和接收/发送规则。该协议以DNS协议为基础，并对其消息格式和消息收发顺序作出了一些修改。例如对DNS消息包头进行了简化，使其专注于实现家庭设备的互相发现；另外，考虑到使用组播技术，mDNS在降低网络拥塞和消息冗余方面也作出了很多改进，使得局域网内设备和服务的发现不会引起过多的消息交互。
在mDNS协议的基础上，DNS-SD协议（http://tools.ietf.org/html/draft-cheshire-dnsext-dns-sd-11）规定了一个服务宣告及使用的完整过程。即设备必须发送什么样的mDNS消息才能完整地宣告并描述自己服务。DNS-SD协议使用PTR、SRV和TXT三种类型的记录全面描述了一个服务的类型，名称以及所在主机的IP和端口号等。
当使用DNS-SD协议实现了对设备及服务的发现和描述后，苹果公司的AirPlay协议规定了图片、音频及视频的传输和控制消息格式，从而实现了智能设备之间的媒体共享和协同动作。例如，在通过DNS-SD获得了其他设备及服务的信息（即设备或服务的IP地址及端口号）之后，AirPlay使用HTTP消息实现了图片和视频的传输及控制，使用RSTP协议实现了音频的传输和控制。
目前，苹果官方AirPlay 解决方案只适用于认证过的苹果设备，主要是 iPad、iPhone、Apple TV 以及苹果公司授权伙伴的设备。 
