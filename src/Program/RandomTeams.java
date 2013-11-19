package Program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomTeams {
	private String[] participants;
	private int teams;
	private ArrayList<String> names;
	private StringBuilder sb;

	public RandomTeams(String[] participants, int teams) {
		this.participants = participants;
		this.teams = teams;
	}

	public String generate() {

		names = new ArrayList<String>(Arrays.asList(participants));
		for(int i =0; i<names.size();i++){
			if(names.get(i).length()==0||names.get(i).equals(" ")){
				names.remove(i);
				i--;
			}
		}
		Random rand = new Random();
		sb = new StringBuilder();
		int fordelning2 = 0;
		int count = 0;
		int lagst = (names.size() / teams);
		int rest = (names.size() % teams);
		int index = 0;
		if (rest == 1&&names.size() != 0) {
				for (int j = 0; j < teams; j++) {
					sb.append("Team " + (j + 1) + "\n");
					for (int k = 0; k < lagst + rest; k++) {
						index = rand.nextInt(names.size());
						sb.append(names.get(index) + "\n");
						names.remove(index);
					}
					sb.append("\n\n");
					rest = 0;
				}
			
		} else if (rest > 1) {
			fordelning2 = rest - (rest - 1);
			if (names.size() != 0) {
				for (int j = 0; j < teams; j++) {
					sb.append("Team " + (j + 1) + "\n");
					if (count < rest) {
						for (int k = 0; k < lagst + fordelning2; k++) {
							if (names.size() != 0) {
								index = rand.nextInt(names.size());
								sb.append(names.get(index) + "\n");
								names.remove(index);
							}
						}
						sb.append("\n\n");
						count++;
					} else {
						for (int k = 0; k < lagst; k++) {
							if (names.size() != 0) {
								index = rand.nextInt(names.size());
								sb.append(names.get(index) + "\n");
								names.remove(index);
							}
						}
						sb.append("\n\n");
					}
				}
			}
		} else {
			if (names.size() != 0) {
				for (int j = 0; j < teams; j++) {
					sb.append("Team " + (j + 1) + "\n");
					for (int k = 0; k < lagst; k++) {
						index = rand.nextInt(names.size());
						sb.append(names.get(index) + "\n");
						names.remove(index);
					}
					sb.append("\n\n");
				}
			}
		}

		return sb.toString();
	}

}
