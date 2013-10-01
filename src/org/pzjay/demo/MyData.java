package org.pzjay.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pzjay.demo.sites.*;

public class MyData {
		//
		public static List<Class> clsSns = new ArrayList() {{
			add(WeiBo.class);
			add(RenRen.class);
			add(QZONE.class);
			add(DouBan.class);
		}};
		public static List<Class> clsTech = new ArrayList() {{
			add(GuoKr.class);
			add(Science.class);
			add(SongShu.class);
			add(Zread.class);
		}};
		public static List<Class> clsNews = new ArrayList() {{
			add(FtCn.class);
			add(QQNews.class);
			add(SinaFinance.class);
			add(WallStJournal.class);
		}};
		public static List<Class> clsBBS = new ArrayList() {{
			add(Mop.class);
			add(TieBa.class);
			add(Mitbbs.class);
			add(TianYa.class);
			add(TieXue.class);
		}};
		public static List<Class> clsLife = new ArrayList() {{
			add(QiuBai.class);
			add(JanDan.class);
			add(PenTi.class);
			add(ChouTi.class);
		}};
		public static List<Class> clsShopping = new ArrayList() {{
			add(TaoBao.class);
			add(GanJi.class);
			add(TongCheng58.class);
			add(MoGuJie.class);
			add(LaShou.class);
			add(NuoMi.class);
		}};
		public static List<Class> clsIT = new ArrayList() {{
			add(CnBeta.class);
			add(HuXiu.class);
			add(KR36.class);
			add(Engadget.class);
			add(GeekPark.class);
		}};
		
		public static List<String> titSns = new ArrayList() {
		{
			add("新浪微博");
			add("人人网");
			add("QQ空间");
			add("douban");
		}
	};
	public static List<String> titTech = new ArrayList() {
		{
			add("果壳网");
			add("科学网");
			add("松鼠会");
			add("左岸读书");
		}
	};
	public static List<String> titNews = new ArrayList() {
		{
			add("金融时报");
			add("腾讯新闻");
			add("新浪财经");
			add("华尔街日报");
		}
	};
	public static List<String> titBBS = new ArrayList() {
		{
			add("猫扑");
			add("百度贴吧");
			add("水木社区");
			add("天涯");
			add("铁血军事");
		}
	};
	public static List<String> titLife = new ArrayList() {
		{
			add("糗事百科");
			add("煎蛋");
			add("打喷嚏");
			add("抽屉网");
		}
	};
	public static List<String> titShopping = new ArrayList() {
		{
			add("淘宝");
			add("赶集网");
			add("58同城");
			add("蘑菇街");
			add("拉手团");
			add("糯米团");
		}
	};	
	public static List<String> titIT = new ArrayList() {
		{
			add("cnBeta");
			add("虎嗅网");
			add("36氪");
			add("engadget");
			add("极客公园");
		}
	};
	public static List<Integer> idSns = new ArrayList() {
		{
			add(Integer.valueOf(R.drawable.ic_weibo));
			add(Integer.valueOf(R.drawable.ic_renren));
			add(Integer.valueOf(R.drawable.ic_qzone));
			add(Integer.valueOf(R.drawable.ic_douban));
		}
	};
	public static List<Integer> idTech = new ArrayList() {
		{
			add(Integer.valueOf(R.drawable.ic_guokr));
			add(Integer.valueOf(R.drawable.ic_science));
			add(Integer.valueOf(R.drawable.ic_songshu));
			add(Integer.valueOf(R.drawable.ic_zread));
		}
	};
	public static List<Integer> idNews = new ArrayList() {
		{
			add(Integer.valueOf(R.drawable.ic_ftcn));
			add(Integer.valueOf(R.drawable.ic_qq));
			add(Integer.valueOf(R.drawable.ic_sina));
			add(Integer.valueOf(R.drawable.ic_wallst));
		}
	};
	public static List<Integer> idBBS = new ArrayList() {
		{
			add(Integer.valueOf(R.drawable.ic_mop));
			add(Integer.valueOf(R.drawable.ic_tieba));
			add(Integer.valueOf(R.drawable.ic_mitbbs));
			add(Integer.valueOf(R.drawable.ic_tianya));
			add(Integer.valueOf(R.drawable.ic_tiexue));
		}
	};
	public static List<Integer> idLife = new ArrayList() {
		{
			add(Integer.valueOf(R.drawable.ic_qiubai));
			add(Integer.valueOf(R.drawable.ic_jandan));
			add(Integer.valueOf(R.drawable.ic_penti));
			add(Integer.valueOf(R.drawable.ic_chouti));
		}
	};
	public static List<Integer> idShopping = new ArrayList() {
		{
			add(Integer.valueOf(R.drawable.ic_taobao));
			add(Integer.valueOf(R.drawable.ic_ganji));
			add(Integer.valueOf(R.drawable.ic_58));
			add(Integer.valueOf(R.drawable.ic_mogujie));
			add(Integer.valueOf(R.drawable.ic_lashou));
			add(Integer.valueOf(R.drawable.ic_nuomi));
		}
	};
	public static List<Integer> idIT = new ArrayList() {
		{
			add(Integer.valueOf(R.drawable.ic_cnbeta));
			add(Integer.valueOf(R.drawable.ic_huxiu));
			add(Integer.valueOf(R.drawable.ic_36ke));
			add(Integer.valueOf(R.drawable.ic_engadget));
			add(Integer.valueOf(R.drawable.ic_geekpark));
		}
	};

	public static Map<String, List<Class>> clsMap = new HashMap() {
		{
			put("sns", clsSns);
			put("tech", clsTech);
			put("news", clsNews);
			put("bbs", clsBBS);
			put("life", clsLife);
			put("shopping", clsShopping);
			put("it", clsIT);
		}
	};
	public static Map<String, List<String>> titMap = new HashMap() {
		{
			put("sns", titSns);
			put("tech", titTech);
			put("news", titNews);
			put("bbs", titBBS);
			put("life", titLife);
			put("shopping", titShopping);
			put("it", titIT);
		}
	};
	public static Map<String, List<Integer>> idMap = new HashMap() {
		{
			put("sns", idSns);
			put("tech", idTech);
			put("news", idNews);
			put("bbs", idBBS);
			put("life", idLife);
			put("shopping", idShopping);
			put("it", idIT);
		}
	};
	
	// share js
	public static final String JSSHARETODOUBAN = "javascript:void(function(){var d=document,e=encodeURIComponent,s1=window.getSelection,s2=d.getSelection,s3=d.selection,s=s1?s1():s2?s2():s3?s3.createRange().text:'',r='http://www.douban.com/recommend/?url='+e(d.location.href)+'&title='+e(d.title)+'&sel='+e(s)+'&v=1',x=function(){if(!window.open(r,'douban','toolbar=0,resizable=1,scrollbars=yes,status=1,width=500,height=360'))location.href=r+'&r=1'};if(/Firefox/.test(navigator.userAgent)){setTimeout(x,0)}else{x()}})()";
	public static final String JSSHARETORRLONG = "javascript:void((function() {var opts, bn, sImg, sText, sArea, aImgs, isIE = navigator.userAgent.match(/(msie) ([\\w.]+)/i),show = function(x, y, s) {var sp = {x : window.pageXOffset || (document.documentElement || document.body).scrollLeft,y : window.pageYOffset || (document.documentElement || document.body).scrollTop};if(s=='i'){x = x - 5 - opts.icons[s].width;y = y - 5 - opts.icons[s].height;} else {x = x - opts.icons[s].width*0.4 + 30;y = y - opts.icons[s].height*0.3 + 30;}with (bn.style) {left = x + sp.x + \"px\";top = y + sp.y + \"px\";position = \"absolute\";zIndex = \"9999\";width = opts.icons[s].width + \"px\";height = opts.icons[s].height + \"px\";backgroundImage = \"url(\" + opts.icons[s].src + \")\";display = \"inline-block\";}}, slen = function(str) {var placeholder = new Array(23).join('x');str = str.replace(/(https?|ftp|gopher|telnet|prospero|wais|nntp){1}:\\/\\/\\w*[\\u4E00-\\u9FA5]*((?![\"| |\t|\r|\n]).)+/ig,function(match) {return placeholder + match.substr(171);}).replace(/[^\u0000-\u00ff]/g, \"xx\");return Math.ceil(str.length / 2);}, inArea = function(tgt) {var areas = opts.areas || [];if (!(areas instanceof Array)) {areas = opts.areas = [areas];}sArea = null;for (var i = 0; i < areas.length; i++) {var z = areas[i];if (z && ((z.contains ? z.contains(tgt) : !!(z.compareDocumentPosition(tgt) & 16)) || z == tgt)) {sArea = z;break;}}return areas.length == 0 || sArea != null;}, h = function(e) {e = e || window.event;var type = e.type, tgt = e.target || e.srcElement;if (type == 'mouseover' && !sText) {if (tgt.tagName == \"IMG\" && opts.img_minWidth > 0 && opts.img_minHeight > 0 && inArea(tgt)) {var bound = tgt.getBoundingClientRect();if (tgt.offsetWidth >= opts.img_minWidth && tgt.offsetHeight >= opts.img_minHeight) {show(bound.right, bound.bottom, 'i');sImg = tgt;}} else if (tgt != bn) {sImg = null;bn.style.display = \"none\";}} else if (type == 'mouseup' && tgt != bn) {var str = tgt.tagName == \"IMG\" ? null : (document.selection ? document.selection.createRange().text : document.getSelection()).toString(),sht = (str || '').replace(/^\\s+|\\s+$/g, '').replace(/[\\s\\n]+/g, ' ');if (sht.length >= opts.text_minCount && opts.text_minCount > 0 && inArea(tgt)) {sText = slen(sht) > 228 ? str : sht;var syl = opts.text_hover_style || opts.style;show(e.clientX, e.clientY, 't');if (e.preventDefault)e.preventDefault();} else {sText = null;if (!sImg)bn.style.display = \"none\";}}}, postTarget = function(opts) {var form = document.createElement('form');form.action = opts.url;form.target = opts.target;form.method = 'POST';form.acceptCharset = \"utf-8\";for (var key in opts.params) {var val = opts.params[key];if (val !== null && val !== undefined) {var input = document.createElement('textarea');input.name = key;input.value = val;form.appendChild(input);}}var hidR = document.getElementById('renren-root-hidden');if (!hidR) {hidR = document.createElement('div');var syl = hidR.style;syl.positon = 'absolute';syl.top = '-10000px';syl.width = syl.height = '0px';hidR.id = 'renren-root-hidden';(document.body || document.getElementsByTagName('body')[0]).appendChild(hidR);}hidR.appendChild(form);try {var cst = null;if (isIE && document.charset.toLowerCase() != 'utf-8') {cst = document.charset;document.charset = 'utf-8';}form.submit();} finally {form.parentNode.removeChild(form);if (cst) {document.charset = cst;}}}, onclick = function() {var anchor = sArea ? sArea.name || sArea.id : null, hl = location.href.indexOf('#'),p = {api_key : opts.api_key,url : (hl == -1 ? location.href : location.href.substr(0, hl)) + (anchor ? \"#\" + anchor : ''),title : (document.title || '').substr(0, 30),images : sImg ? sImg.src : aImgs,content : sText};var prm = [];for (var i in p) {if (p[i])prm.push(i + '=' + encodeURIComponent(p[i]));}var url = 'http://widget.renren.com/dialog/forward?' + prm.join('&'),maxLgh = (isIE? 2048 : 4100), wa = 'width=700,height=650,left=0,top=0,resizable=yes,scrollbars=1';if (url.length > maxLgh) {window.open('about:blank', 'fwd', wa);postTarget({url : 'http://widget.renren.com/dialog/forward',target : 'fwd',params : p});} else {window.open(url, 'fwd', wa);}return false;}, cfg = function(op) {opts = op || window.__fwdCfg || {};opts.img_minWidth = opts.img_minWidth === 0 ? 0 : opts.img_minWidth || 100;opts.img_minHeight = opts.img_minHeight === 0 ? 0 : opts.img_minHeight || 100;opts.text_minCount = opts.text_minCount === 0 ? 0 : opts.text_minCount || 5;opts.icons = {t:{src:'http://s.xnimg.cn/actimg/forward/img/icon-1-1.png', width:78, height:25}, i:{src:'http://s.xnimg.cn/actimg/forward/img/icon-1-1.png', width:78, height:25}};if(opts.text_hover_icon){var tcn = document.createElement(\"img\"), ims = opts.text_hover_icon;tcn.onload = function(){tcn.onload = null;opts.icons.t = tcn;};tcn.src = ims.indexOf('http://') == 0? ims : 'http://s.xnimg.cn/actimg/forward/img/' + ims;}if(opts.img_hover_icon){var icn = document.createElement(\"img\"), ims = opts.img_hover_icon;icn.onload = function(){icn.onload = null;opts.icons.i = icn;};icn.src = ims.indexOf('http://') == 0? ims : 'http://s.xnimg.cn/actimg/forward/img/' + ims;}}, init = function() {if (init.isReady || document.readyState !== 'complete')return;bn = document.createElement(\"a\");var bsyl = \"height:26px;position:absolute;background:no-repeat transparent;display:none;padding:0;margin:0;border:0;\";bn.setAttribute('href', 'javascript:;');bn.setAttribute('title', '\u5C06\u5185\u5BB9\u8F6C\u53D1\u81F3\u4EBA\u4EBA\u7F51');bn.setAttribute('style', bsyl);bn.style.cssText = bsyl;(document.body || document.getElementsByTagName('body')[0]).appendChild(bn);var imgs = document.getElementsByTagName('img'), imga = [];for (var i = 0; i < imgs.length; i++) {if (imgs[i].width >= opts.img_minWidth && imgs[i].height >= opts.img_minHeight) {imga.push(imgs[i].src);}}if (imga.length > 0)aImgs = imga.join('|');if (document.addEventListener) {document.addEventListener('mouseup', h, false);document.addEventListener('mouseover', h, false);bn.addEventListener('click', onclick, false);document.removeEventListener('DOMContentLoaded', init, false);} else {document.attachEvent('onmouseup', h);document.attachEvent('onmouseover', h);bn.attachEvent('onclick', onclick);document.detachEvent('onreadystatechange', init);}init.isReady = true;};cfg();window.Renren = window.Renren || {};window.Renren.forward = cfg;if (document.readyState === 'complete') {init();} else if (document.addEventListener) {document.addEventListener('DOMContentLoaded', init, false);window.addEventListener('load', init, false);} else {document.attachEvent('onreadystatechange', init);window.attachEvent('onload', init);}})());";
	public static final String JSSHARETORR = "javascript:void((function(){var script=document.createElement('script');script.setAttribute('type', 'text/javascript');script.setAttribute('src','http://widget.renren.com/js/forward.js');script.setAttribute('async', 'true');document.body.appendChild(script);})());";
	public static final String JSSHARETORR2 = "javascript:void((function(){var script=document.createElement('script');script.setAttribute('type', 'text/javascript');script.setAttribute('src','http://static.connect.renren.com/js/share.js');document.body.appendChild(script);})());";
	public static String share_to_sina_weibo = "javascript:void((function(){window.open('http://v.t.sina.com.cn/share/share.php?searchPic=false&title=test','newwindow','height=100,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')})());";
	public static String js_share_to_renren = "javascript:void((function(s,d,e){var f='http://share.renren.com/share/buttonshare?link=',u=location.href,l='',p=[e(u),'&title=',e(l)].join('');p=\"test by pzjay\";function a(){if(!window.open([f,p].join(''),'xnshare',['toolbar=0,status=0,resizable=1,width=626,height=436,left=',(s.width-626)/2,',top=',(s.height-436)/2].join('')))u.href=[f,p].join('');};if(/Firefox/.test(navigator.userAgent))setTimeout(a,0);else a();})(screen,document,encodeURIComponent));";
	
	// UA
	public static final String UA_UC = "Mozilla/5.0 (Linux;U;Android 2.1-update1;en-us;E15i Build/2.0.1.A.0.47)UC AppleWebKit/536.5 (KHTML, like Gecko)Moile Safari/530";
	public static final String whatsmyua = "http://whatsmyuseragent.com/";
	public static final String UA_SM = "Mozilla/5.0 (Linux; Android 2.1.0) AppleWebKit/536.5 (KHTML, like Gecko) Social Master/1.0";
	public static final String UA_CHROME = "Mozilla/5.0 (Linux; U; Android 2.3.7; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
	public static final String UA_CHROME_PC = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.56 Safari/536.5";
	public static final String UA_UC_OLD = "JUC (Linux; U; 2.3.7; zh-cn; MB200; 320*480) UCWEB7.9.3.103/139/999";
	public static final String UA_QB = "MQQBrowser/26 Mozilla/5.0 (Linux; U; Android 2.3.7; zh-cn; MB200 Build/GRJ22; CyanogenMod-7) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
	public static final String UA_OPERA = "Opera/9.80 (Android 2.3.4; Linux; Opera Mobi/build-1107180945; U; en-GB) Presto/2.8.149 Version/11.10";

	// url
	public static final String WEB2WAP = "http://somtp.iask.cn/html2wml?url=";
	// sns
	public static final String PENGYOU = "http://pt.3g.qq.com/g/s?aid=cpgamelogin&cpid=700&gameid=7901&go_url=http%3A%2F%2Ff.qq.com%2Findex.php%3Fmod%3Dhome%26fromlogin%3D1%26flag%3D9";
	public static final String QZONE = "http://pt.3g.qq.com/s?aid=nLoginqz&sid=Ab_f6xbR0NCPGSZqHUlmRPiV&KqqWap_Act=3&g_ut=2&go_url=http%3A%2F%2Fz.qq.com%2Findex.jsp";
	public static final String TQQ = "http://pt.3g.qq.com/s?aid=nLoginmb&amp;g_ut=2&amp;go_url=http%3A%2F%2Fti.3g.qq.com%2Fg%2Fs%3Faid%3Dloginjump%26g_ut%3D2%26coid%3D";
	//public static final String TQQ = "http://t.3g.qq.com/";
	public static final String DOUBAN = "http://m.douban.com/login?redir=%2F%3Fsession%3Db44ea43f&session=08b5f236";
	public static final String RR = "http://3g.renren.com/";
	public static final String WEIBO = "http://m.weibo.cn/?tf=7_010&st=331c";
	
	// it
	public static final String CNBETA_SRC = "http://cnbeta.com/";
	public static final String CNBETA = WEB2WAP + "http://cnbeta.com/";
	public static final String GEEKPARK = "http://m.geekpark.net/";
	public static final String HUXIU_SRC = "http://huxiu.com/";//////////////
	public static final String HUXIU = WEB2WAP + "http://huxiu.com/";//////////////
	public static final String KR36 = "http://www.36kr.com/wap/";
	public static final String ENGADGET = "http://mobile.engadget.com/";
	
	// tech
	public static final String GUOKR_WAP = "http://m.guokr.com/";
	public static final String GUOKR2 = "http://m.guokr.com/#!/";
	public static final String GUOKR = "http://www.guokr.com/";
	public static final String SCIENCE = "http://wap.sciencenet.cn/";
	public static final String SONGSHU = "http://songshuhui.net/";
	public static final String SONGSHU_WAP = WEB2WAP + "http://songshuhui.net/";
	public static final String ZREAD = "http://wap.zreading.cn";
	
	// bbs
	public static final String MITBBS = "http://www.newsmth.net/";
	public static final String TIEBA = "http://tieba.baidu.com";
	public static final String MOP = "http://m.mop.com/";
	public static final String TIANYA = "http://m.tianya.cn/";
	public static final String TIEXUE = "http://m.tiexue.net/3G/";
	
	// life
	public static final String QIUBAI = "http://m.qiushibaike.com/";
	public static final String JANDAN = "http://i.jandan.net/";
	public static final String PENTI = "http://m.dapenti.com/";
	public static final String CHOUTI = "http://www.chouti.com/";
	
	// shopping
	public static final String TAOBAO = "http://m.taobao.com/";
	public static final String GANJI = "http://3g.ganji.cn/";
	public static final String TC58 = "http://m.58.com/";
	public static final String MOGUJIE = "http://m.mogujie.com/";
	public static final String LASHOU = "http://m.lashou.com/";
	public static final String NUOMI = "http://m.nuomi.com/";
	
	// news
	public static final String SOHU = "http://m.sohu.com/?v=3";
	public static final String FTCN = "http://m.ftchinese.com/m.html";
	//public static final String NETEASE = "http://sina.cn";
	public static final String QQNEWS = "http://3g.qq.com/";
	public static final String SINAFINANCE = "http://finance.sina.cn/";
	public static final String WALLST = "http://m.cn.wsj.com";
}
