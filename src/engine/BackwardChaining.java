package engine;

import java.util.ArrayList;

public class BackwardChaining extends Method {
	BackwardChaining(ArrayList<String> kb, ArrayList<String> symbols, String query) {
		name = "Backward Chaining";
		knowledgeBase = kb;
		this.symbols = symbols;
		this.query = query;
	}

	public void check() {
		// Initialization
		ArrayList<String> agenda = new ArrayList<String>();
		ArrayList<String> entailed = new ArrayList<String>();
		// Add the query to agenda
		agenda.add(query);

		// while the list of symbols are not empty
		while (!agenda.isEmpty()) {
			// get symbol from end of agenda
			String s = agenda.remove(agenda.size() - 1);
			// add to entailed
			entailed.add(s);
			
			for (int i = 0; i < knowledgeBase.size(); i++) {
				String[] sentenceSymbols = getSentenceSymbols(knowledgeBase.get(i));
				if (sentenceSymbols.length > 1) {
					// if symbol is conclusion of sentence
					if (sentenceSymbols[sentenceSymbols.length - 1].equals(s)) {
						for (int j = 0; j < sentenceSymbols.length - 1; j++) {
							if (!agenda.contains(sentenceSymbols[j])) {
								agenda.add(sentenceSymbols[j]);
							}
						}
					}
				}
			}
		}
		if (entailed.size() > 1) {
			System.out.println("YES: ");
			for (int i = 0; i < entailed.size() - 1; i++) {
				System.out.print(entailed.get(i) + ", ");
			}
			System.out.println(entailed.get(entailed.size() - 1));
		}
		else {
			System.out.println("NO");
		}
	}
}
