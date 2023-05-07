-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.40-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema reading_share
--

CREATE DATABASE IF NOT EXISTS reading_share;
USE reading_share;

--
-- Definition of table `t_admin`
--

DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `danwei` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `keshi` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_admin`
--

/*!40000 ALTER TABLE `t_admin` DISABLE KEYS */;
INSERT INTO `t_admin` (`id`,`username`,`password`,`danwei`,`phone`,`keshi`,`name`) VALUES 
 (1,'admin','admin','清华大学','13365445566','儿科','张三');
/*!40000 ALTER TABLE `t_admin` ENABLE KEYS */;


--
-- Definition of table `t_baoming`
--

DROP TABLE IF EXISTS `t_baoming`;
CREATE TABLE `t_baoming` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bioid` int(10) unsigned DEFAULT NULL,
  `userid` int(10) unsigned DEFAULT NULL,
  `updatedate` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_baoming`
--

/*!40000 ALTER TABLE `t_baoming` DISABLE KEYS */;
INSERT INTO `t_baoming` (`id`,`bioid`,`userid`,`updatedate`,`username`,`tel`,`status`) VALUES 
 (1,7,1,'2023-02-25 13:27:02','hehe','13325240001','1'),
 (2,7,2,'2023-02-25 13:31:02','wang','13325240002','1');
/*!40000 ALTER TABLE `t_baoming` ENABLE KEYS */;


--
-- Definition of table `t_biotech`
--

DROP TABLE IF EXISTS `t_biotech`;
CREATE TABLE `t_biotech` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `author` varchar(45) DEFAULT NULL,
  `content` varchar(5000) DEFAULT NULL,
  `image_url` varchar(45) DEFAULT NULL,
  `pubdate` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `status` int(10) unsigned DEFAULT NULL,
  `zan` int(10) unsigned DEFAULT NULL,
  `type2` varchar(45) DEFAULT NULL,
  `zuobiao` varchar(45) DEFAULT NULL,
  `hours` int(10) unsigned DEFAULT '0',
  `huodong_date` varchar(45) DEFAULT NULL,
  `huodong_addr` varchar(45) DEFAULT NULL,
  `type_id` int(10) unsigned DEFAULT NULL,
  `count_` int(10) unsigned DEFAULT '0',
  `count_1` int(10) unsigned DEFAULT '0',
  `status2` int(10) unsigned DEFAULT '0',
  `fuzeren` varchar(45) DEFAULT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `xingzhi` varchar(45) DEFAULT NULL,
  `ishaibao` varchar(45) DEFAULT NULL,
  `age` int(10) unsigned DEFAULT '0',
  `favorite` int(10) unsigned DEFAULT '0',
  `jubao` int(10) unsigned DEFAULT '0',
  `message` int(10) unsigned DEFAULT '0',
  `comments` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_biotech`
--

/*!40000 ALTER TABLE `t_biotech` DISABLE KEYS */;
INSERT INTO `t_biotech` (`id`,`author`,`content`,`image_url`,`pubdate`,`title`,`type`,`status`,`zan`,`type2`,`zuobiao`,`hours`,`huodong_date`,`huodong_addr`,`type_id`,`count_`,`count_1`,`status2`,`fuzeren`,`tel`,`xingzhi`,`ishaibao`,`age`,`favorite`,`jubao`,`message`,`comments`) VALUES 
 (1,'admin','人民政协网北京2月24日电 2月23日，由中国出版协会主办，浙江教育出版集团协办，北京书友之家文化交流有限公司和中国少年儿童阅读推广联盟共同承办的儿童主题出版论坛在京成功举办。作为第35届北京图书订货会的特色活动，本次论坛聚焦“新征程、新阅读、新分享”主题，围绕少儿主题出版的发展趋势、内容价值、市场表现、运作机制等多个维度进行深入交流和讨论。','upload\\1113706395.jpg','2023-02-25 11:37:06','“新征程 新阅读 新分享”儿童主题出版论坛举办','1',1,2,'通知公告',NULL,0,NULL,NULL,0,0,0,0,NULL,NULL,NULL,NULL,0,2,1,1,2),
 (2,'admin','为引导广大群众尤其是青少年厚植爱党爱国情怀，弘扬和传承红色文化，展现澄海区践行“全民阅读”成果，2月24日上午，2023年汕头市全民阅读系列活动之“强国复兴有我”——阅读分享会在澄海中学初中部新时代文明实践点举行。','upload\\1113804253.jpeg','2023-02-25 11:38:04','【推进全民阅读】“强国复兴有我”','1',1,0,'通知公告',NULL,0,NULL,NULL,0,0,0,0,NULL,NULL,NULL,NULL,0,0,0,0,0),
 (3,'admin','“爱是教育的灵魂，只有爱学生才能正确对待，宽容学生所犯的错误，才能耐心的去雕塑每一位学生。我读了李振西《做最好的班主任》这本书后，对构建和谐师生关系的重要性深受启发。”2月22日，鲁山县让河乡第三小学教师张亚鹏在该乡组织的读书分享会上分享自己的读书心得时说。','upload\\1113932875.jpg','2023-02-25 11:39:32','河南鲁山：阅读书香浓 分享共成长','1',1,0,'通知公告',NULL,0,NULL,NULL,0,0,0,0,NULL,NULL,NULL,NULL,0,1,0,0,0),
 (4,'康乔华','在基层乡镇工作的时间久了，有时会觉得生活变得有些枯燥、单调和乏味。这种感觉，对于在公平山乡工作的我来说更是如此。由于山乡离我在农村的九屋老家较远，乘车来回跑一趟需花上两个多小时，每个星期如果不用加班，除了周末顺利回到家休息外，其余时间基本上都是在山乡里度过的。在山乡这段时间，尽管工作繁锁、烦恼和焦躁，但我却因书籍或阅读使自己的生活变得轻松、惬意。我感受到更多的阅读快乐，仿佛找到了人生前进的灯塔。\r\n\r\n山乡工作的环境是艰苦的，我所住的地方就是原先乡里的老计生楼改建成的职工住宿楼。据说我现在所住在一楼的那间宿舍是计生站曾经的办公室，面积不足十平方米。记得来山乡报到的那天，我提着一摞用麻绳捆好的书走进这个房间，房间里唯一的床和桌子成了我摆放书籍的地方。这些书大多是我从老家书房精挑细选来的，有鲁迅的《野草》、路遥的《平凡的世界》、塞格林的《麦田的守望者》、铁凝的《村路带我回家》、梭罗的《瓦尔登湖》等许多国内外作家的着作。在这里，宿舍同时成了书房。人在房间，抬头是书，低头是书，左右四周还是书，于是我不由自主地驶进了书的海洋。\r\n\r\n有人曾说，书是有命的。这些书在山乡里与我相依相伴，也算与我有了同一个归宿。虽然，独自远离家乡来到这个陌生的山区工作，内心有时会油然产生莫名的孤独感。但看到房间里这些与我朝夕相伴的书时，像是遇到了良师益友，感到莫大的喜悦。可以说，我的人生志趣、心路轨迹都因这些书发生了变化。当我劳累了一天，从工作岗位上拖着疲惫的躯体回到这一方陋室，环顾房间那些书时，我的心灵忽然就有了依靠的感觉。于是，我开始独坐在桌前，泡上一杯清茶，随意地从书桌上拿起书进行阅读，我的心就此安定下来。我的思绪便会随着书中情节或人物事件一点点融入进去，竟然就情不自禁地与作者共同品味对生活的体验和感悟。\r\n\r\n我幸福地浸在书的意境与回味里，往往读着读着，茶已凉透却忘记喝，一天工作的劳累早以悄然逸去，留下的是思绪的欢快和清新。这时，我的肉身也会因某些时刻的深入阅读而获得心灵的轻盈和洁净。在山乡呆久了，我还特别喜欢那种在乡间蛙声中读书的感觉，蛙声如雨，此起彼伏。密密麻麻的蛙声，像密密麻麻的农事。晚归的农人，把农事一搁，温上一壶浊酒，沉浸在有滋有味的日子中。我很向往这样的农家生活，在工作之外，我常常学着山乡里的农人，把手头的杂事交给白天，坐下来，安静地翻动书本，让密密麻麻的文字像密密麻麻的小蝌蚪，在昏黄的灯光下游动，“蛙声十里出清泉”。这时，或许整个山乡的时间就属于我自己了。\r\n\r\n山乡的时间是闲适的，读书使这种闲适增添了灵性。在这种灵性的生活中，我平时除了阅读自己房间里的书外，还常常到圩街上开的一家租书店里逛，寻找自己喜欢看的书，花些零用钱租一些书回来，细细翻阅。在山乡陆续认识了一些爱读书的朋友之后，只要得知他们家里经常有新书买回来，我都会经常去拜访。随便找个什么借口打个招呼，然后就坐在人家家里看书。常常看到了饭点，自己还浑然不觉。有时，人家干脆把书借给我，让我拿回家看，等看完之后再还回来。书里的豪侠江湖、情真意切、成长感悟填满了我的山乡生活，支撑着我坚定地一路前行。就这样，在阅读与现实之间，我的生活又因书籍增添了别样的情趣。\r\n\r\n对书籍的品味和领会充实了我平凡的生活。在山乡，我的人生与阅读同行，尽管自己的阅读无论广度还是深度都无法和老师相比，但聊以自慰的是我始终对书不离不弃。因此，我每次出门远行，除了带够足够的衣服，就是带书。坐在摇摇晃晃的车厢里，像是坐在摇篮里，字在纸张上飞，车厢在铁轨上飞，眼神在车窗外的田野上飞，真的是很惬意的。还有，我平时周末回九屋老家，从山乡车站开始坐汽车到达那里，需要花很长时间。在车上的时间特别难熬，尤其是遇到交通堵塞，心里就更加焦躁。这时倘若有一本好书在手中，便能瞬间度过漫长时光。在汽车上读书，只要真的读进去，就能旁若无人，就像在自己的书房里读书一样，任何躁声都不可能干扰我的情绪。于是可以说，读书是惬意、舒服、自由的人生享受，把我的身心内外调理得熨熨帖帖的。我每天向书房里摆放的书籍行着注目礼，这是我对书籍和文字的一种感恩，一种敬意。\r\n\r\n书，真的可以改变人生，改变一个人命运的走向。我的眼界在开阔，精神在成长，对生活的认识和领悟在加深。我是热爱生命的人，在这个充满喧嚣与浮躁的尘世里，是书给我带来了不同的体验，使我在潜移默化中丰富了自我，它能让我随时保持平和、宽松的心态，去感受生活。','upload\\1114749223.jpg','2023-02-25 11:47:49','指引我的灯塔','3',1,0,'阅读精选',NULL,0,NULL,NULL,0,0,0,0,NULL,NULL,NULL,NULL,0,0,0,0,0),
 (5,'龚光程','马克·吐温曾说：“有好朋友、好书、没有良心上的责备，这就是幸福人生。”老马的这个观点我一直奉为圭臬。书是人类智慧的结晶，能给人以知识，给人以智慧，给人以快乐，给人以希望。我仅上过10年学堂，在艰难困苦中，书一直是我的希望之灯，我从阅读中走出人生的暗夜，获得了今天这份安稳的生活。\r\n\r\n上个世纪80年代，那是物质生活与精神生活都相对贫乏的年代。我刚读初中。与现在的学生相比，学习轻松许多；但可读之书，除了课本别无他物。饥渴的我们每天都在四处搜寻课外书，偶得一本，必欣喜若狂，废寝忘食。那时书虽不像现在这样昂贵，买一本300页的书不过六七块钱，但那已经是我在校半个月的生活费了，买书看，那只能是一个非常奢侈的梦想。一次进城，见大佛洞旁边的梧桐树下有一辆架子车，上面挂满图书，旁边摆着几条长凳，那时候还没有撵摊的城管，十余老少坐在那里看书，神态甚为享受。我心中大喜，走到架子旁下欲取书看，一老者近身道：“小鬼，两角钱看一次！”原来这是一家专事租书的摊点。忙问：“可以看好久？”答：“可以看一天。”搜遍全身口袋，竟然找出两角。于是交钱看书，却苦于读者众而无凳可坐，只得行蹲读之法。\r\n\r\n自此之后，一有机会就去那儿看书，也不拘何书。大约一个月后的周末，仍旧去那看书，交上两毛钱，摘下书。见有空凳子便坐下了，却被老板客气地叫住了：“坐位子要三毛。”有这种事？我这才察觉新老板的不同；但似乎又无理可讲。摸遍身上的包包，就是没有多余的一毛板凳钱！只得又蹲着看，反正是练熟了这种姿势的。过了些时辰，着得脚踝酸痛，便立而读之，如此反复变换，居然坚持了一个下午的时间，只是傍晚回去时走路显得踉踉跄跄的。\r\n\r\n弹指一挥间，二十作年过去了，我一直保持着闲暇阅读的习惯，阅读给了我无限的快乐，工作之余，一壶清茶，一卷在手，茶香盈室，书面轻翻，心旌微摇。有高山流水鸣于耳畔，瘦菊幽兰馨于鼻尖，芳草连天，长亭古道，红有樱桃，绿有芭蕉……“三更有梦书当枕”，书香盈鼻入梦来，这是何等的惬意。\r\n\r\n传播学者麦克鲁连认为：“衣服是皮肤的延伸，轮子是脚的延伸。书是眼睛的延伸。”说得对极了，我们的眼睛无法阅遍世界，看尽人间，但是，通过读书，我们就可以在方寸之地，立即扩大原有的经验空间、精神空间、想象空间与美感空间。会使你心驰神飞，奇思遐想，思接千载，视通万里，可朝谒秦皇汉武，幕访孔孟太白，夏可观十里荷花，秋赏三秋桂子，北可驰骋于塞外草原，南可踯躅于江春雨巷……\r\n\r\n梁实秋说：“书，什么不给你呢？足不出户，而卧游千山万水；素不相识，可以促膝谈心，给城市人以乡村的风光，给乡村人以城市的繁华。年老的无妨读血气刚盛的人冒险的故事，年轻也可以学饱经世故的长者的经验。”\r\n\r\n有这样一个故事，著名探险家利文斯顿在非洲各部落旅行时，只要有空闲，总不忘记打开手边的书来阅读，而且常常一读就是好几个钟头。\r\n\r\n部落居民都觉得莫名其妙，以为那些“白纸黑字的怪物”里，一定藏着什么神秘的“好东西”，不然，利文斯顿怎么会如此痴迷其中。\r\n\r\n有一天，趁利文斯顿不在的时候，几个部落居民便偷偷地溜进他的住处，把书页撕下来，放进嘴里咀嚼后吞下去。\r\n\r\n这些部落居民吃书是因为他们的环境里没有书，心里全无“书”的概念！更感觉不到阅读的乐趣。但如果我们生活在一个有书的世界里，却不去读书。假如有一天，生命中再也没有可以引发我们“阅读”的冲动，或者，对再美好再亲近的书，我们都失去了“阅读”它们的本事，那么，活着，就少了许多灵魂悸动的快乐！\r\n\r\n一部好书就是尘世里的一盏明灯，照亮了人们的心灵，也照亮了人类历史的路径。每一次细细地品读，就是一次心灵的远行。书把人类引导向那有着瑰丽风景的思想远方，去共进一场精神的盛宴。有人认为，读书的三重境界，一为读得知识，长学问，从而高雅；二为感悟世界，增智慧，变得聪明；三为修炼人品，提高境界，充实人生。通过阅读，我们不一定变得更加富有，但我们一定可以变得更加智慧。通过阅读，我们不一定能延长我们生命的长度，但一定可以改变我们生命的宽度，增加我们生命的厚度。通过阅读，我们不一定能够兼济天下，但我们一定能够独善其身。\r\n\r\n读书是人生的一种境界。','upload\\1114844447.jpeg','2023-02-25 11:48:44','书是尘世里的一盏明灯','3',1,1,'阅读精选',NULL,0,NULL,NULL,0,0,0,0,NULL,NULL,NULL,NULL,0,0,0,0,0),
 (6,'黎武静','“止”是一个有智慧的字。而《古文观止》是一本精彩绝伦的书，因为它将许多的精华集订成册，目不暇接地呈现给你看。\r\n\r\n有许多经典都告诉我们“止”的智慧，《大学》里有云：“知止而后有定，定而后能静，静而后能安，安而后能虑，虑而后能得。”我原本并不能领会它的佳处，缤纷世界流光溢彩，贪恋痴迷当然多多益善。然而当我在图书馆沉迷欲醉时才发现，止原来是一种我求之未得的智慧。\r\n\r\n有人说一生读不了三千本书，或许说得太过仓促。世事无绝对，一目十行者也许一生囫囵吞枣也能吞得许多书，加上那些杂七杂八的阅读，也许远远不止三千。然而，我们一生中有价值的阅读，其实又有多少？有多少书籍是可以为我们照亮灵魂的花园，有多少书籍是可以激起我们深沉的思考？\r\n\r\n当站在时间的桥上，看它逝者如斯，不舍昼夜，便知即使极速奔驰，也追不过时间的脚步。有这有限的时间里，如何最大限度最有效地利用时间，便是一门高深莫测的学问。毕竟，每一段时间都是独一无二的，在时间的总数值之内，那些无效用无价值的阅读占去的时间，侵占了更美好的阅读的可能。\r\n\r\n“生活中不是缺少美，而是缺少发现美的眼睛。”换句话说，生活中不是缺少美，而是缺少分辩美的眼睛。我们读许多年的书，懂得分辨好书与不好的书，便是最大的收获。在图书馆的丛林里，一眼望去，整排整排的书架里，单看那些书脊上的简短文字，便能猜出这本书将要揭开的未知旅程，待到看清封面与扉页，它的阅读轮廓已经若隐若现。\r\n\r\n伏尔泰慨叹于图书馆的广博与芜杂，他说，“图书馆是真理与谬误的宝库”，而狄德罗和他的意思差不多，“我们这些巨大的图书馆，是才华成品和文字垃圾的共同汇集地。”\r\n\r\n物以稀为贵。凿壁借光、读遍京城书坊注定了是属于古时的传奇，当文字从甲骨、金石、巾帛、纸张发展到电子格式，载体越来越方便易得，信息量越来越繁杂庞巨，我们该怎样面对？要学会在书山乱径间止步。\r\n\r\n我常常在图书馆前迷惑，像站在一所庞大的迷宫面前。我阅读的速度追不上它扩张的速度，在这漫无止境的乐土，选择和舍弃某些不必要的阅读，也许才是求索的关键。\r\n\r\n未必人人要做训诂方家，皓首穷经。除却那些为工作的专业阅读，在生活的余暇里，阅读是一件私人的事情。我们心里都有一份属于自己的挚爱书单，某些带着偏爱的目录。尽情任性，读自己爱读的那一部分，凭着感觉渐次地读下去，在时间上的前后缓急，无意间便做出了选择。\r\n\r\n“止”是一种必要的选择，除此之外，我们别无选择，因为时间有限，图书馆无限。','upload\\1114941092.jpeg','2023-02-25 11:49:41','智慧于止','3',1,1,'阅读精选',NULL,0,NULL,NULL,0,0,0,0,NULL,NULL,NULL,NULL,0,1,0,0,0),
 (7,'hehe','分享阅读心得','upload\\1132619390.jpeg','2023-02-25 13:26:19','新年悦读会','2',0,2,NULL,NULL,3,'2023-02-28','北京',1,3,2,1,NULL,NULL,NULL,NULL,1,1,1,1,1),
 (8,'hehe','大家好','upload\\1132804356.jpeg','2023-02-25 13:28:04','hello','0',1,0,'科技类',NULL,0,NULL,NULL,1,0,0,0,NULL,NULL,NULL,NULL,0,0,0,0,0),
 (9,'wang','OK','upload\\1133214069.jpg','2023-02-25 13:32:14','阅读分享','0',1,0,'科技类',NULL,0,NULL,NULL,1,0,0,0,NULL,NULL,NULL,NULL,0,0,0,0,0),
 (10,'wang','share reading','upload\\1133427945.jpg','2023-02-25 13:34:27','reading','2',0,0,NULL,NULL,3,'2023-02-28','hello',1,3,0,1,NULL,NULL,NULL,NULL,1,0,0,0,0),
 (11,'wang','hello','upload\\1133458614.jpg','2023-02-25 13:34:58','hello','1',1,0,'通知公告',NULL,0,NULL,NULL,0,0,0,0,NULL,NULL,NULL,NULL,0,0,0,0,0);
/*!40000 ALTER TABLE `t_biotech` ENABLE KEYS */;


--
-- Definition of table `t_comments`
--

DROP TABLE IF EXISTS `t_comments`;
CREATE TABLE `t_comments` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `commitdate` datetime DEFAULT NULL,
  `content` varchar(45) DEFAULT NULL,
  `luxianid` int(10) unsigned DEFAULT NULL,
  `userid` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_comments`
--

/*!40000 ALTER TABLE `t_comments` DISABLE KEYS */;
INSERT INTO `t_comments` (`id`,`username`,`commitdate`,`content`,`luxianid`,`userid`) VALUES 
 (1,'hehe','2023-02-25 13:20:47','ok',1,1),
 (2,NULL,'2023-02-25 13:31:08','123',7,2),
 (3,NULL,'2023-02-25 13:31:31','1',1,2);
/*!40000 ALTER TABLE `t_comments` ENABLE KEYS */;


--
-- Definition of table `t_favorite`
--

DROP TABLE IF EXISTS `t_favorite`;
CREATE TABLE `t_favorite` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `duanziid` int(10) unsigned DEFAULT NULL,
  `userid` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_favorite`
--

/*!40000 ALTER TABLE `t_favorite` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_favorite` ENABLE KEYS */;


--
-- Definition of table `t_folder`
--

DROP TABLE IF EXISTS `t_folder`;
CREATE TABLE `t_folder` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `duanziid` int(10) unsigned DEFAULT NULL,
  `userid` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_folder`
--

/*!40000 ALTER TABLE `t_folder` DISABLE KEYS */;
INSERT INTO `t_folder` (`id`,`duanziid`,`userid`) VALUES 
 (1,1,1),
 (2,7,2),
 (3,1,2),
 (4,3,2),
 (5,6,2);
/*!40000 ALTER TABLE `t_folder` ENABLE KEYS */;


--
-- Definition of table `t_friends`
--

DROP TABLE IF EXISTS `t_friends`;
CREATE TABLE `t_friends` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userid1` int(10) unsigned DEFAULT NULL,
  `userid2` int(10) unsigned DEFAULT NULL,
  `status` int(10) unsigned DEFAULT '0',
  `black_status` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_friends`
--

/*!40000 ALTER TABLE `t_friends` DISABLE KEYS */;
INSERT INTO `t_friends` (`id`,`userid1`,`userid2`,`status`,`black_status`) VALUES 
 (1,2,1,1,0);
/*!40000 ALTER TABLE `t_friends` ENABLE KEYS */;


--
-- Definition of table `t_history`
--

DROP TABLE IF EXISTS `t_history`;
CREATE TABLE `t_history` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bioid` int(10) unsigned DEFAULT NULL,
  `userid` int(10) unsigned DEFAULT NULL,
  `updatedate` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_history`
--

/*!40000 ALTER TABLE `t_history` DISABLE KEYS */;
INSERT INTO `t_history` (`id`,`bioid`,`userid`,`updatedate`,`title`,`username`,`author`) VALUES 
 (1,1,1,'2023-02-25 11:50:46','“新征程 新阅读 新分享”儿童主题出版论坛举办','hehe','admin'),
 (2,6,1,'2023-02-25 11:50:53','智慧于止','hehe','黎武静'),
 (3,5,1,'2023-02-25 11:51:07','书是尘世里的一盏明灯','hehe','龚光程'),
 (4,1,1,'2023-02-25 13:20:38','“新征程 新阅读 新分享”儿童主题出版论坛举办','hehe','admin'),
 (5,1,1,'2023-02-25 13:20:40','“新征程 新阅读 新分享”儿童主题出版论坛举办','hehe','admin'),
 (6,1,1,'2023-02-25 13:20:41','“新征程 新阅读 新分享”儿童主题出版论坛举办','hehe','admin'),
 (7,1,1,'2023-02-25 13:20:47','“新征程 新阅读 新分享”儿童主题出版论坛举办','hehe','admin'),
 (8,1,1,'2023-02-25 13:20:49','“新征程 新阅读 新分享”儿童主题出版论坛举办','hehe','admin'),
 (9,1,1,'2023-02-25 13:20:54','“新征程 新阅读 新分享”儿童主题出版论坛举办','hehe','admin'),
 (10,7,1,'2023-02-25 13:27:01','新年悦读会','hehe','hehe'),
 (11,7,1,'2023-02-25 13:27:03','新年悦读会','hehe','hehe'),
 (12,7,1,'2023-02-25 13:27:05','新年悦读会','hehe','hehe'),
 (13,8,1,'2023-02-25 13:28:07','hello','hehe','hehe'),
 (14,7,2,'2023-02-25 13:31:01','新年悦读会','wang','hehe'),
 (15,7,2,'2023-02-25 13:31:02','新年悦读会','wang','hehe'),
 (16,7,2,'2023-02-25 13:31:03','新年悦读会','wang','hehe'),
 (17,7,2,'2023-02-25 13:31:03','新年悦读会','wang','hehe'),
 (18,7,2,'2023-02-25 13:31:09','新年悦读会','wang','hehe'),
 (19,7,2,'2023-02-25 13:31:11','新年悦读会','wang','hehe'),
 (20,7,2,'2023-02-25 13:31:14','新年悦读会','wang','hehe'),
 (21,7,2,'2023-02-25 13:31:19','新年悦读会','wang','hehe'),
 (22,1,2,'2023-02-25 13:31:24','“新征程 新阅读 新分享”儿童主题出版论坛举办','wang','admin'),
 (23,1,2,'2023-02-25 13:31:26','“新征程 新阅读 新分享”儿童主题出版论坛举办','wang','admin'),
 (24,1,2,'2023-02-25 13:31:26','“新征程 新阅读 新分享”儿童主题出版论坛举办','wang','admin'),
 (25,1,2,'2023-02-25 13:31:31','“新征程 新阅读 新分享”儿童主题出版论坛举办','wang','admin'),
 (26,3,2,'2023-02-25 13:31:37','河南鲁山：阅读书香浓 分享共成长','wang','admin'),
 (27,3,2,'2023-02-25 13:31:40','河南鲁山：阅读书香浓 分享共成长','wang','admin'),
 (28,8,2,'2023-02-25 13:31:47','hello','wang','hehe'),
 (29,9,2,'2023-02-25 13:32:20','阅读分享','wang','wang'),
 (30,6,2,'2023-02-25 13:32:39','智慧于止','wang','黎武静'),
 (31,6,2,'2023-02-25 13:32:44','智慧于止','wang','黎武静'),
 (32,6,2,'2023-02-25 13:32:45','智慧于止','wang','黎武静'),
 (33,5,2,'2023-02-25 13:32:51','书是尘世里的一盏明灯','wang','龚光程'),
 (34,5,2,'2023-02-25 13:32:54','书是尘世里的一盏明灯','wang','龚光程'),
 (35,6,2,'2023-02-25 13:33:03','智慧于止','wang','黎武静'),
 (36,11,2,'2023-02-25 13:35:04','hello','wang','wang');
/*!40000 ALTER TABLE `t_history` ENABLE KEYS */;


--
-- Definition of table `t_jubao`
--

DROP TABLE IF EXISTS `t_jubao`;
CREATE TABLE `t_jubao` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `duanziid` int(10) unsigned DEFAULT NULL,
  `userid` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_jubao`
--

/*!40000 ALTER TABLE `t_jubao` DISABLE KEYS */;
INSERT INTO `t_jubao` (`id`,`duanziid`,`userid`) VALUES 
 (1,1,1),
 (2,7,2);
/*!40000 ALTER TABLE `t_jubao` ENABLE KEYS */;


--
-- Definition of table `t_message`
--

DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cdate` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `username` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `touserid` int(10) unsigned DEFAULT NULL,
  `userid` int(10) unsigned DEFAULT NULL,
  `reply` varchar(100) DEFAULT NULL,
  `luxianid` int(10) unsigned DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_message`
--

/*!40000 ALTER TABLE `t_message` DISABLE KEYS */;
INSERT INTO `t_message` (`id`,`cdate`,`content`,`username`,`touserid`,`userid`,`reply`,`luxianid`,`author`) VALUES 
 (1,'2023-02-25 13:20:54','1','hehe',0,1,NULL,1,'admin'),
 (2,'2023-02-25 13:31:18','123','wang',0,2,'1',7,'hehe');
/*!40000 ALTER TABLE `t_message` ENABLE KEYS */;


--
-- Definition of table `t_shetuan`
--

DROP TABLE IF EXISTS `t_shetuan`;
CREATE TABLE `t_shetuan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_shetuan`
--

/*!40000 ALTER TABLE `t_shetuan` DISABLE KEYS */;
INSERT INTO `t_shetuan` (`id`,`name`,`content`) VALUES 
 (1,'科技类','科技类'),
 (2,'文学类','文学类'),
 (3,'艺术类','艺术类'),
 (4,'创意类','创意类'),
 (5,'运动类','运动类'),
 (6,'娱乐类','娱乐类');
/*!40000 ALTER TABLE `t_shetuan` ENABLE KEYS */;


--
-- Definition of table `t_sign`
--

DROP TABLE IF EXISTS `t_sign`;
CREATE TABLE `t_sign` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(10) unsigned DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `signdate` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_sign`
--

/*!40000 ALTER TABLE `t_sign` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sign` ENABLE KEYS */;


--
-- Definition of table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `qqnum` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `score` int(10) unsigned DEFAULT '0',
  `hours` int(10) unsigned DEFAULT '0',
  `status` int(10) unsigned DEFAULT '0',
  `image_url` varchar(45) DEFAULT NULL,
  `age` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_user`
--

/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` (`id`,`username`,`password`,`qqnum`,`phone`,`name`,`address`,`score`,`hours`,`status`,`image_url`,`age`) VALUES 
 (1,'hehe','111111','呵呵','13325240001','管理员','科技类',0,3,1,'upload\\1132112105.jpg','1'),
 (2,'wang','111111','王','13325240002','管理员','科技类',0,3,1,'upload\\1133344837.jpeg','1');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;


--
-- Definition of table `t_zan`
--

DROP TABLE IF EXISTS `t_zan`;
CREATE TABLE `t_zan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `duanziid` int(10) unsigned DEFAULT NULL,
  `userid` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_zan`
--

/*!40000 ALTER TABLE `t_zan` DISABLE KEYS */;
INSERT INTO `t_zan` (`id`,`duanziid`,`userid`) VALUES 
 (1,1,1),
 (2,7,1),
 (3,7,2),
 (4,1,2),
 (5,6,2),
 (6,5,2);
/*!40000 ALTER TABLE `t_zan` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
