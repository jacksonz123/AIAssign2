package engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ForwardChaining extends Method {
	ForwardChaining(ArrayList<String> kb, ArrayList<String> symbols, String query) {
		name = "Forward Chaining";
		knowledgeBase = kb;
		this.symbols = symbols;
		this.query = query;
	}

	public ArrayList<String> getInitialAgenda() {
		ArrayList<String> agenda = new ArrayList<String>();
		for (int i = 0; i < knowledgeBase.size(); i++) {
			String[] operators = getSentenceOperators(knowledgeBase.get(i));
			// if kb has set symbol to true (no operators in sentence)
			if (operators == null) {
				String symbol = getSentenceSymbols(knowledgeBase.get(i))[0];
				agenda.add(symbol);
			}
		}

		return agenda;
	}

	public Map<Integer, Integer> getInitialCountTable() {
		Map<Integer, Integer> table = new HashMap<Integer, Integer>();
		for (int i = 0; i < knowledgeBase.size(); i++) {
			String[] operators = getSentenceOperators(knowledgeBase.get(i));
			// if kb has set symbol to true (no operators in sentence)
			if (operators == null) {

			} else {
				// put index and count and number of symbols in premise
				table.put(i, getSentenceSymbols(knowledgeBase.get(i)).length - 1);
			}
		}
		return table;
	}

	public void check() {
		// Check that KB is in Horn Form
		for (int i = 0; i < knowledgeBase.size(); i++) {
			if (!checkHornForm(knowledgeBase.get(i))) {
				System.out.println("Invalid Input, ensure in Horn Form");
				System.exit(1);
			}
		}
		// Map of index of sentence in KB and count of premises in sentences
		Map<Integer, Integer> table = new HashMap<Integer, Integer>(getInitialCountTable());
		ArrayList<String> inferred = new ArrayList<String>();
		ArrayList<String> agenda = new ArrayList<String>(getInitialAgenda());

		while (!agenda.isEmpty()) {
			String p = agenda.remove(0);
			inferred.add(p);
			// if query is entailed, print results
			if (p.equals(query)) {
				System.out.print("YES: ");
				for (int i = 0; i < inferred.size() - 1; i++) {
					System.out.print(inferred.get(i) + ", ");
				}
				System.out.println(inferred.get(inferred.size() - 1));
				return;
			}
			for (int i = 0; i < knowledgeBase.size(); i++) {
				String[] sentenceSymbols = getSentenceSymbols(knowledgeBase.get(i));
				if (Arrays.asList(sentenceSymbols).contains(p)) {
					if (table.get(i) == null) {
						// do nothing, this is for already inferred symbols
					} else if (table.get(i) == 1) {
						table.put(i, table.get(i) - 1);
						// Check if not already inferred
						if (!inferred.contains(sentenceSymbols[sentenceSymbols.length - 1])) {
							// Add the last symbol in sentence to agenda as its premise count is now 0
							agenda.add(sentenceSymbols[sentenceSymbols.length - 1]);
						}
					} else {
						table.put(i, table.get(i) - 1);
					}
				}
			}
		}
		// If not entailed
		System.out.println("NO");
	}
}
