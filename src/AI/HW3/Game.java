package HW3;

import java.util.ArrayList;
import java.util.Random;

class Game {

	Matrix m;

	// The following vector was evolved for 50,000 generations
	static double[] bob = {-0.008489817867144821,-0.1655558657658649,-0.04109212630067639,-0.03363903894959527,0.1557537733716787,0.1906170962095113,-0.24227521391876491,-0.14519209851185666,0.011456528002300043,-0.19867441232985836,0.07058697458242709,0.0078096717677792835,-0.1287001484633393,-0.021896029506265652,0.13317018494566316,-0.0028526085433170107,0.029287882387138933,-0.2365914190664137,0.08189165121518648,-0.02439158718181125,-0.0754850852379157,0.15589987468175953,0.1621220530599791,0.16367047430540269,-0.08442939767169659,0.009722826399512587,0.1399861453446846,-0.18025331053449528,-0.16763745710186015,0.12394440800398986,-0.07052397175755604,-0.06874409606896148,-0.09046267138354284,-0.09750796703245294,0.01910464749718896,0.15598772253932566,0.04274857150378246,0.12001200761132892,-0.08330872727840756,-0.36664446362081554,-0.0011835627925908215,0.11290365082216229,-0.11374118066935725,0.02338388944154191,0.10258872199479331,0.21487501714533966,-0.04088318419774445,
			-0.01454559236974949,0.14987572233541557,0.12901447477265401,-0.05342818952473737,-8.008291281853775E-4,0.20052888845975944,-0.08248489480139877,-0.007526149071247082,-0.029735238865249928,0.10313042325768199,-0.24274518432310632,0.20651955317183585,-0.10257815987736353,-0.06642250494563078,0.0702748434741088,-0.04515443520142777,-0.06332637218546841,0.022677674608297872,-0.04077989756440211,0.0634910468153172,0.11196253658158772,0.015531944512919844,0.051606452266932196,0.013795133123258687,-0.0498953666157562,0.0909670839794224,-0.08117156904374694,0.04278990651787432,0.04161942219618173,0.20291738082428787,-0.06382438921165733,-0.1016536751539541,-0.0411462065439327,-0.04280851826122679,-0.029453348500932015,-0.16555884149282116,-0.08398959799464212,-0.09236740528941761,-0.12072249083728404,0.04162827825688545,0.17047727351233544,0.04916786869023413,-0.12371017774792911,0.04249874209872587,0.14603709165881554,0.016949268736496458,-0.0056131509610774545,
			-0.10730587444857655,-0.0763671964061504,-0.13060731428630687,-0.08477332208064033,0.02840456683308846,-0.04964718685165834,-0.031736726477492753,0.12759776349344273,-0.10037282834186219,-0.23469642973100696,-0.07176126396306347,0.017435419116013234,0.07996966777911615,-0.0063709217572325665,0.10723388756718077,-0.061695178573823464,0.0960483201778395,-0.01626209303473402,-0.020489561834590546,-0.04738957199425209,0.07970667432107828,0.04392491355434258,-0.1346537259160428,0.13331355906936065,-0.009487713796268026,-0.0192957751148489,-0.06341054302865844,0.14107235299117327,-0.18428056501496085,0.027424000623879924,0.1730653489020766,-0.22620846474103612,0.05592627043654277,-0.10255041829186126,0.06030111623753805,-0.13679694691523533,-0.21119239931475023,-0.037026664414232004,-0.062003315758790735,0.1113858633584873,0.2661824019370583,0.09347624963442791,0.15575976741004513,-0.10787530459573351,0.10632796239064492,-1.9105248841757086E-4,-0.026789656384475575,
			-0.2578654701008404,0.15513803097820408,0.0747275535356669,-0.03994096822258086,-0.060024493619449885,0.0773346303666542,-0.03685911995350865,-0.24649894288631097,-0.05979176491161458,-0.025234580881962506,-0.08745446401702109,-0.17281329747630225,0.14222564086173134,0.1427986311012735,-0.12982582326422587,-0.02724648352394331,-0.3175639642408998,0.156274937907814,0.06524231492134458,-0.1337927139576156,-0.17685864050280237,-0.01167797007704318,-0.08656195382325342,-0.02584387237145829,0.1362782261811175,-0.23705002617783089,-0.08908661546601672,0.027238978548035753,0.3038242621890848,0.008286665019485816,-0.21966202622837167,0.10266841490828507,-0.09891896764294496,0.2264140306176794,0.13744935555811588,-0.03775841138809757,0.18110118275408615,-0.1006485716575716,-0.1636011870944607,-0.08038875394149271,-0.0356106339174031,-0.006643686123113857,0.055969137629886076,-0.062161504836139234,-0.08604933747414231,0.10641275631531416,-0.12876146143640657,-0.04918419563666607,
			-0.028601260409834982,-0.08064725575505664,-0.01862707584741917,-0.2719451958912039,0.1259121131774493,-0.10821366402345818,0.09540456520577495,-0.12032850233893112,-0.038647900210426904,0.28080242078273376,0.05927650731435651,-0.16457081841755383,0.056422407751959176,-0.07856923844286996,-0.014625545494006555,0.24065089559531824,-0.01191756246690779,-0.029053824225917613,0.22426795794772805,0.03515857627288339,0.10916815805019264,-0.2640653244388694,-0.08174313130576855,-0.3999913667578743,-0.057358080666630014,-0.09700332052995415,-0.03898873209182132,7.626608601245459E-4,-0.006951247871750911,-0.24240702610291678,0.0498197788159902,-0.1973728003681408,-0.15120368152846658,0.260936056584657,-0.20877541056071053,-0.11691413781474361,-0.008202418742836379,-0.11014250815901332,-0.16714662024113025,0.002365031300182786,-0.0684201228829415,0.12242078107168679,-0.14137180148739917,0.07046789856114244,0.07998734161092619,0.15720365772075168,-0.10452780665463055,
			-0.13115002052070882,-0.054208987714354964,0.13978956865788014,-0.15418868808004876,-0.05562315832291495,0.15593722498435186,-0.032081819305820214,0.019146057585709524,-0.04741361240267623,-0.17460553631619752,0.03857974562263165,-0.012667417822684005,0.04444505240524968,-0.07213775451517185,0.06825787862882832,0.07263501474127551,-0.17388248180049184,-0.10633218643713854,-0.09984572595910164,-0.11898780212082638,-0.18664360435098737,-0.0038235544997647425,0.0021441553631073506,0.275673901332478,0.0032647967502588884,0.1701197240979516,0.08298299976005095,0.04526141387270879,0.20573518182479397,-0.0848708856325639,-0.1324311990119111,0.05782602407624469,0.35757931136925325,-0.06984476664202775,-0.08120931208637083,-0.2045088180771483,-0.035069545298939284,-0.17384682370619675,-0.2731356022563814,-0.055230968403176164,0.24200796935123417,-0.15756146684386124,-0.05958190749553855,0.009219352609714536,0.13045179193496628,-0.3482368769068052,-0.05396953114716504,
			0.09006518584694838,-0.09276188802323124,-0.12268590292443704,0.14021564255193278,0.1956167035703967,-0.03109619850814607,0.15264495326782712,-0.385147417034142};

	public static void main(String[] args) throws Exception {
		Random r = new Random(1234);
		double[] randy = new double[291];
		for(int i = 0; i < 291; i++)
			randy[i] = 0.01 * r.nextGaussian();
		//Controller.doBattle(new NeuralAgent(randy), new NeuralAgent(bob));
		//Controller.doBattle(new NeuralAgent(bob), new NeuralAgent(bob));
		//Controller.doBattle(new HumanAgent(), new NeuralAgent(bob));
		
		Evolver evolver = new Evolver();
	}
}
