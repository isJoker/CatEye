package com.wjc.cateye.utils;

/**
 * Created by ${万嘉诚} on 2016/11/29.
 * WeChat：wjc398556712
 * Function：常量类
 */

public class Constans {

    //欢迎页北京
    public static final String SPLASH_IMAGE = "http://p0.meituan.net/movie/a0dd83495941a47c1cd34d2972e2d0df59585.jpg";

    //热映页ViewPager
    public static final String HOT_SHOW_VIEWPAGER = "http://advert.mobile.meituan.com/api/v3/adverts?cityid=1&category=11&version=6.8.0&new=0&app=movie&clienttp=android&uuid=FCFAB9D8DD339645D629C8372A29A2C6AD16F9C9E87AF9AC0D656B29DD5AC6DE&devid=866641027400542&uid=&movieid=&partner=1&apptype=1&smId=&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=qq&utm_medium=android&utm_term=6.8.0&utm_content=866641027400542&ci=1&net=255&dModel=HM%20NOTE%201LTETD&lat=40.100855&lng=116.378273&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463730432992&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=01f9c5c0-eb56-4e19-92fb-b86b16ad79da&__skcy=5K8wRR%2FKYAZDTgmAzbhrXi%2FomzU%3D";

    //热映页ListView
    public static final String HOT_SHOW_LISTVIEW = "http://m.maoyan.com/movie/list.json?type=hot&offset=0&limit=1000";

    //待映页Listview
    public static final String WILL_SHOW_LISTVIEW = "http://api.meituan.com/mmdb/movie/v2/list/rt/order/coming.json?ci=1&limit=12&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=xiaomi&utm_medium=android&utm_term=6.8.0&utm_content=868030022327462&net=255&dModel=MI%205&uuid=0894DE03C76F6045D55977B6D4E32B7F3C6AAB02F9CEA042987B380EC5687C43&lat=40.100673&lng=116.378619&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463704714271&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=1a0b4a9b-44ec-42fc-b110-ead68bcc2824&__skcy=sXcDKbGi20CGXQPPZvhCU3%2FkzdE%3D";

    //待映预告片
    public static final String WAITE_YUGAO = "http://api.maoyan.com/mmdb/movie/lp/list.json";

    //待映最受期待
    public static final String WAITE_ZUIQIDAI = "http://api.maoyan.com/mmdb/movie/v1/list/wish/order/coming.json?offset=0&limit=50&ci=1";

    //全球电影奖
    public static final String MOIVE_AWARDS = "http://api.meituan.com/mmdb/movie/region/festival/list.json?__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&uuid=2C2C0ECD557F366849954BEF88D0017AC98AD8183EC9108E7D92853C8D3EF972&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463704641616&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=072ad6f1-c10b-4c49-a076-74baae3b231e&__skcy=4UbKeGaCLKKn%2BqhEjZmJ96heHsE%3D";

    //热映口碑
    public static final String FIND_HOT_KOUBEI = "http://api.meituan.com/mmdb/movieboard/fixedboard/7.json?offset=0&limit=10&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieC110189035496448D-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&uuid=2C2C0ECD557F366849954BEF88D0017AC98AD8183EC9108E7D92853C8D3EF972&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463727372645&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=26a19547-c4ad-438f-96af-0feadadba2b0&__skcy=S9pqtpXxrUm%2BgC2THRjoEuUzJoY%3D";

    //最受期待
    public static final String FIND_ZUI_QIDAI = "http://api.meituan.com/mmdb/movieboard/fixedboard/6.json?offset=0&limit=10&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieC110189035496448D-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&uuid=2C2C0ECD557F366849954BEF88D0017AC98AD8183EC9108E7D92853C8D3EF972&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463727423837&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=bbd36610-f22a-4523-bcfb-59658c25056e&__skcy=Phj0IYaOnVVHPXIHPumsV6vBBrg%3D";

    //TOP100
    public static final String FIND_TOP_100 = "http://api.meituan.com/mmdb/movieboard/fixedboard/4.json?offset=0&limit=10&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&uuid=2C2C0ECD557F366849954BEF88D0017AC98AD8183EC9108E7D92853C8D3EF972&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463712334518&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=7a1d1ed9-473e-49e9-ad52-b1b7bbd5a5b7&__skcy=nXjScwb2c0H8dO0LxNXZGfODzoI%3D";

    //海外电影--美国--韩国--日本
    public static final String FIND_HAI_WAI = "http://api.meituan.com/mmdb/movie/oversea/areas.json?token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=xiaomi&utm_medium=android&utm_term=6.8.0&utm_content=868030022327462&ci=1&net=255&dModel=MI%205&uuid=0894DE03C76F6045D55977B6D4E32B7F3C6AAB02F9CEA042987B380EC5687C43&lat=40.100673&lng=116.378619&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463704930917&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=e3df01ab-d811-4ff8-868d-9b4a665ee352&__skcy=FZG8h%2Fw31ebpODD3EnjMbBAPTiE%3D";

    //美国
    public static final String FIND_USA = "http://api.meituan.com/mmdb/movie/oversea/coming.json?area=NA&offset=0&limit=10&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=860311023305964&ci=1&net=255&dModel=MI%203&uuid=F096369352E4004DD3758BF83FE24AC312445F9B51AD3D8FCE2A0CD57754F6E4&lat=40.099337&lng=116.379463&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463979002816&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=ff7d7258-bf35-49b1-8350-438616853e0b&__skcy=oNlmWR0dK8QqRyoPuJ9SQPBnvk0%3D";

    //韩国
    public static final String FIND_HANGUO = "http://api.meituan.com/mmdb/movie/oversea/hot.json?area=KR&offset=0&limit=10&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=860311023305964&ci=1&net=255&dModel=MI%203&uuid=F096369352E4004DD3758BF83FE24AC312445F9B51AD3D8FCE2A0CD57754F6E4&lat=40.099337&lng=116.379463&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463979233089&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=5245fc26-6931-4d8e-8202-4fa32944ea28&__skcy=IQYM%2F257Pcv7b%2FfH8U60gxAsVWg%3Dhttp://api.meituan.com/mmdb/movie/oversea/hot.json?area=KR&offset=0&limit=10&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=860311023305964&ci=1&net=255&dModel=MI%203&uuid=F096369352E4004DD3758BF83FE24AC312445F9B51AD3D8FCE2A0CD57754F6E4&lat=40.099337&lng=116.379463&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463979233089&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=5245fc26-6931-4d8e-8202-4fa32944ea28&__skcy=IQYM%2F257Pcv7b%2FfH8U60gxAsVWg%3D";

    //日本
    public static final String FIND_JAPAN = "http://api.meituan.com/mmdb/movie/oversea/hot.json?area=JP&offset=10&limit=10&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=860311023305964&ci=1&net=255&dModel=MI%203&uuid=F096369352E4004DD3758BF83FE24AC312445F9B51AD3D8FCE2A0CD57754F6E4&lat=40.099337&lng=116.379463&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463979347735&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=81a99289-f2ad-4e77-b39c-c899764cbf0a&__skcy=ekzQcZxVLz93KYT7pqaKbPIu%2FGo%3D";

    //全部电影奖项--最下面
    public static final String FID_QUANQIUJIANG = "http://api.meituan.com/mmdb/movie/region/festival/list.json?__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&uuid=2C2C0ECD557F366849954BEF88D0017AC98AD8183EC9108E7D92853C8D3EF972&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463704641616&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=072ad6f1-c10b-4c49-a076-74baae3b231e&__skcy=4UbKeGaCLKKn%2BqhEjZmJ96heHsE%3D";


    public static final String FIND_HEAD_TITLE = "http://api.maoyan.com/sns/v2/buttons.json?utm_campaign=AmovieBmovieCD-1&movieBundleVersion=7501&utm_source=baidu2-dy&utm_medium=android&utm_term=7.5.0&utm_content=162785154327873&ci=1&net=255&dModel=CHM-TL00H&uuid=DD248CD671940B81A053CD16752CB1ED59F2F7716F6B2B1FDBA408C200CE86CD&lat=39.86921443098112&lng=116.42276039489884&__reqTraceID=4230162655252054367&refer=%2FShowActivity&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1480484674073&__skua=32bcf146c756ecefe7535b95816908e3&__skno=ccfae640-2bd5-4c64-97f0-b3652d31c073&__skcy=nQams8Z9xvIXamNUn8MBXUlPP%2Bg%3D";
    public static final String FIND_BODY_MSG = "http://api.maoyan.com/sns/v5/feed.json?offset=";
    public static final String FIND_BODY_MSGLAST = "&limit=10&timestamp=0&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=7501&utm_source=baidu2-dy&utm_medium=android&utm_term=7.5.0&utm_content=162785154327873&ci=1&net=255&dModel=CHM-TL00H&uuid=DD248CD671940B81A053CD16752CB1ED59F2F7716F6B2B1FDBA408C200CE86CD&lat=39.86921443098112&lng=116.42276039489884&__reqTraceID=289405745981786362&refer=%2FShowActivity&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1480484674048&__skua=32bcf146c756ecefe7535b95816908e3&__skno=24603878-2b32-4e55-86fc-1234e2b2c2a3&__skcy=lqDuUKMWOxn3O24ylqjgk4uMWws%3D";
//  http://api.maoyan.com/sns/v5/feed.json?offset=10&limit=10&timestamp=0&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=7501&utm_source=baidu2-dy&utm_medium=android&utm_term=7.5.0&utm_content=162785154327873&ci=1&net=255&dModel=CHM-TL00H&uuid=DD248CD671940B81A053CD16752CB1ED59F2F7716F6B2B1FDBA408C200CE86CD&lat=39.86921443098112&lng=116.42276039489884&__reqTraceID=289405745981786362&refer=%2FShowActivity&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1480484674048&__skua=32bcf146c756ecefe7535b95816908e3&__skno=24603878-2b32-4e55-86fc-1234e2b2c2a3&__skcy=lqDuUKMWOxn3O24ylqjgk4uMWws%3D

    /**
     * 发现界面的Top10
     */
    public  static  final String  DISCOVER_TOP10 = "http://m.maoyan.com/information?_v_=yes&groupId=1481354&pageType=1&title=%E4%BB%8A%E6%97%A5TOP10&pushToken=dpshe3234c9ecd8e748e1eeae12b0ac4b78fatpu&f=android&cityId=1&userid=-1&cityId=1";
    /**
     * 发现界面的影视快讯
     */
    public  static  final String DISCOVER_NEWS= "http://m.maoyan.com/information?_v_=yes&f=android&userid=-1&pushToken=dpshe3234c9ecd8e748e1eeae12b0ac4b78fatpu&cityId=1";
    /**
     * 发现界面的实时票房
     */
    public static final String DISCOVER_TICKET="http://piaofang.maoyan.com/?f=android&userid=-1&pushToken=dpshe3234c9ecd8e748e1eeae12b0ac4b78fatpu&cityId=1";

    public static final String CINAME = "http://m.maoyan.com/cinemas.json";


}
