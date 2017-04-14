package HW8;

import java.util.ArrayList;

class Game {

	static void fullTournament() throws Exception {
		ArrayList<IAgent> al = new ArrayList<IAgent>();
		/*
		al.add(new PrescientMoron());
		al.add(new Mixed());
		al.add(new Human());
		al.add(new Blitz());
		al.add(new SittingDuck());
		al.add(new AggressivePack());
		*/
		al.add(new JosephGauthier());
		al.add(new Winner2015a());
		al.add(new Winner2015b());
		al.add(new Winner2016a());

		Controller.doTournament(al);
	}

	public static void main(String[] args) throws Exception {
		Controller.doBattle(new JosephGauthier(), new Winner2016a());
		//System.out.println(Controller.doBattleNoGui(new JosephGauthier(), new Winner2016a()));
		//fullTournament();
	}
}
