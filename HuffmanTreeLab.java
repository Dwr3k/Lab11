import java.io.FileInputStream;
import java.io.IOException;

public class HuffmanTreeLab {

	public static void main(String[] args) {
		String fileName = args[0];
		int[] charCounts = getCharacterFrequency(fileName);
		Node root = getHuffmanTree(charCounts);
		String[] huffCode = getCodes(root);

		// Display chart of ASCII code, Character, Frequency, Huffman Code
		System.out.printf("%-15s%-15s%-15s%-15s\n", "ASCII Code", "Character", "Frequency", "Code");
		for(int i = 0; i < charCounts.length; i++) {
			if(charCounts[i] != 0) {
				System.out.printf("%-15s%-15s%-15s%-15s\n", i, (char)i, charCounts[i], huffCode[i]);
			}
		} 
	}
	
	public static String[] makeString(Node root, String[] codes, String codeTracker)
	{
		
		
		if(root.left == null && root.right == null)
		{
			int index = Integer.valueOf(root.element);
			codes[index] = codeTracker;
			return codes;
		}
		
		makeString(root.left, codes, codeTracker + "0");
		makeString(root.right, codes, codeTracker + "1");
		
		return codes;
	}

	//	Get codes for each letter
	public static String[] getCodes(Node root) {
		if(root == null) {
			return null;
		}
		String[] result = new String[256];
		assignCodes(root, result);
		return result;
	}

	// Assigns the Huffman code to each node in the Huffman Code Tree 
	// and records the code in code array 
	private static void assignCodes(Node root, String[] codes) 
	{	
		String codeTracker = "";
		codes = makeString(root, codes, codeTracker);
	}

	/** Get a Huffman tree from the codes */  
	public static Node getHuffmanTree(int[] counts) {
		// Create a Dictionary to hold the forest of trees
		SortedLinkedDictionary<Integer, Node> forest = new SortedLinkedDictionary<>(); 

		HuffmanTreeLab test = new HuffmanTreeLab();

		for(int i = 0; i < counts.length; ++i)
		{
			if(counts[i] != 0)
			{
				forest.add(counts[i], test.new Node(counts[i], (char) i));
			}
		}

		while(forest.getSize() != 1)
		{
			Node first = forest.remove(forest.getKeyIterator().next());
			Node second = forest.remove(forest.getKeyIterator().next());
			
			int combinedWeight = first.weight + second.weight;

			Node newTree = test.new Node(combinedWeight, String.valueOf(combinedWeight).charAt(0));
			
			newTree.weight = combinedWeight;
			
			if(first.compareTo(second) == 1)
			{
				newTree.left = first;
				first.code = "0";
				
				newTree.right = second;
				second.code = "1";
			}
			else if(first.compareTo(second) == 0)
			{
				newTree.left = first;
				first.code = "0";
				
				newTree.right = second;
				second.code = "1";
			}
			else
			{
				newTree.left = second;
				second.code = "0";
				
				newTree.right = first;
				first.code = "1";
			}
			
			newTree.code = first.code + second.code;
			
			forest.add(combinedWeight, newTree);
		}



		// combine trees in the forest into a single tree


		return forest.remove(forest.getKeyIterator().next()); // The final tree
	}

	/** Get the frequency of the characters */
	public static int[] getCharacterFrequency(String filename) {    
		int[] counts = new int[256]; // 256 ASCII characters

		try {
			FileInputStream input = new FileInputStream(filename);
			int inputByte = input.read();

			while (inputByte != -1) {
				if(Character.isLetter((char)inputByte))
					counts[inputByte]++;
				inputByte = input.read();
			}
			input.close();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}

		return counts;
	}


	class Node implements Comparable<Node>{
		char element;     // Stores the character for a leaf node
		int weight;       // weight of the subtree rooted at this node
		Node left;        // Reference to the left subtree
		Node right;       // Reference to the right subtree
		String code = ""; // The code of this node from the root


		/** Create a node with the specified weight and character */
		public Node(int weight, char element) {
			this.weight = weight;
			this.element = element;
		}

		@Override /** Compare trees based on their weights */
		public int compareTo(Node o) {
			if (this.weight < o.weight) { // Purposely reverse the order
				return 1;
			}
			else if (this.weight == o.weight) {
				return 0;
			}
			else {
				return -1;
			}
		}
	}

}
