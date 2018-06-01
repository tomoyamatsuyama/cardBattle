package B1;

import java.util.*;

public class Play implements P.Player {
	List<Integer> list = Arrays.asList(2,3,4,5,6,7,8,9);
	List<Integer> winnerList = new ArrayList<>();
	int maxScore = -500;
	List<Integer> yourCard;

	int listCount = 0;

	List<Integer> hokan = new ArrayList<>();

	public int call (
		int[] mycard, int[] yourcard, 
		int[] mylog, int[] yourlog, 
		double mylastscore, double yourlastscore, 
		double mytotalscore, double yourtotalscore, 
		int count, long timeleft
	)
	{
		int submit = 0;
		java.util.Random r = new java.util.Random();
		if (count == 1) {
			return 10;
		} else if (count == 2) {
			return 13;
		} else if (count == 3) {
			return 1;
		} else if (count == 4) {
			return 12;
		} else if (count == 5) {
			return 11;
		} else if (count == 6) {
			this.yourCard = checkOfYourCard(yourcard);
			calcurate(new ArrayList<>(), new ArrayList<>(), 0);
		}
		return this.winnerList.get(count - 6);
	}

	private List<Integer> checkOfYourCard(int[] card) {
		int index = 0;
		List<Integer> lis = new ArrayList<>();
		for(int i=1; i < card.length; i++) {
			if (card[i] == 0) {
				lis.add(i);
			}
		}
		return lis;
	}

	private void calcurate(List<Integer> usedI, List<Integer> usedJ, int score) {
		listCount++;
		if (listCount > 40320) {
			return;
		}

		if (usedI.size() == list.size()) {
			if (score > this.maxScore) {
				this.maxScore = score;
				this.winnerList.clear();
				this.winnerList.addAll(this.hokan);
				this.hokan.clear();
				// System.out.println(this.winnerList);
				usedI.clear();
				usedJ.clear();
				return;
			}
			usedI.clear();
			usedJ.clear();
			return;
		}

		for(Integer i: list) {
			for(Integer j: yourCard) {
				if (!usedI.contains(i) && !usedJ.contains(j)) {
					List<Integer> newListI = new ArrayList<>();
					usedI.add(i);
					newListI.addAll(usedI);
					this.hokan.addAll(usedI);

					List<Integer> newListJ = new ArrayList<>();
					newListJ.addAll(usedJ);
					newListJ.add(j);
					calcurate(newListI, newListJ, calcOfScore(i, j, score));
				}
			}
		}
	}

	private int calcOfScore(Integer i, Integer j, int score) {
		if (i < j) {
			return (score -(15 - j));
		} else if (i > j) {
			return (score + (15 - i));
		} else {
			return (score + (i / 2));
		}
	}
}
