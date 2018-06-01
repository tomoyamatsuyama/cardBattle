package B2;

public class Play implements P.Player {

	int[] deck = {
	8,9,7,10,6,11,5,12,4,13,2,3,1
	};

	public int call (
		int[] mycard, int[] yourcard,
		int[] mylog, int[] yourlog,
		double mylastscore, double yourlastscore,
		double mytotalscore, double yourtotalscore,
		int count, long timeleft
	)
	{
//私の13枚カードの可能的得点
		double[] myscore;
		myscore =new double[13];

		if (mycard[1]==0) {
			double n=0;
			if (yourcard[1]==0) n=n+0.5;
			for (int q=3; q<=13; q++){
				if (yourcard[q]==0) n++;
			}
			myscore[0]=n;
		}else {
			myscore[0]=0;
		}

		if (mycard[2]==0) {
			double n=0;
			if (yourcard[1]==0) n=n++;
			if (yourcard[2]==0) n=n+0.5;
			myscore[1]=n*13;
		}else {
			myscore[1]=0;
		}

		for (int i=3; i<=13;i++){
			double n=0;
			if (mycard[i]==0) {
				for (int j=2; j<i; j++){
					if (yourcard[j]==0) n++;
				}
				if (yourcard[i]==0) n=n+0.5;
        myscore[i-1]=n*(15-i);
		  }else {
			  myscore[i-1]=0;
		  }
		}

//相手の13枚カードの可能的得点
		double[] yourscore;
		yourscore =new double[13];

		if (yourcard[1]==0) {
			double n=0;
			if (mycard[1]==0) n=n+0.5;
			for (int q=3; q<=13; q++){
				if (mycard[q]==0) n++;
			}
			yourscore[0]=n;
		}else {
			yourscore[0]=0;
		}

		if (yourcard[2]==0) {
			double n=0;
			if (mycard[1]==0) n=n++;
			if (mycard[2]==0) n=n+0.5;
			yourscore[1]=n*13;
		}else {
			yourscore[1]=0;
		}

		for (int i=3; i<=13;i++){
			double n=0;
			if (yourcard[i]==0) {
				for (int j=2; j<i; j++){
					if (mycard[j]==0) n++;
				}
				if (mycard[i]==0) n=n+0.5;
				yourscore[i-1]=n*(15-i);
			}else {
				yourscore[i-1]=0;
			}
		}

//比較
		double minusscore=100;
		double yourmaxscore=-1;
		int submit=0;
		for (int p=1; p<=13; p++){
			if (yourcard[p]==0 && yourscore[p-1] > yourmaxscore) {
				yourmaxscore = yourscore[p-1];
			}
		}
		for (int p=1; p<=13; p++){
			if (mycard[p]==0 && Math.abs(myscore[p-1]-yourmaxscore) < minusscore) {
        minusscore = Math.abs(myscore[p-1]-yourmaxscore);
				submit = p;
			}
		}

	  //double mymaxscore=-1;
		//for (int p=1; p<=13; p++){
		//	if (mycard[p]==0 && myscore[p-1] > mymaxscore) {
		//		mymaxscore = myscore[p-1];
		//		submit = p;
		//	}
		//}

    return submit;
		//return deck[count-1];
	}

}
