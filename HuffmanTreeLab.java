
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
		// TODO 

	}

	/** Get a Huffman tree from the codes */  
	public static Node getHuffmanTree(int[] counts) {
		// Create a Dictionary to hold the forest of trees
		SortedLinkedDictionary<Integer, Node> forest = new SortedLinkedDictionary<>(); 

		HuffmanTreeLab heck = new HuffmanTreeLab();

		for(int i = 0; i < counts.length; ++i) 
		{
			if(counts[i] != 0)
			{
				Node temp = heck.new Node(counts[i], (char) i);
				forest.add(counts[i], temp);
			}
		}

		// TODO 
		while(forest.getSize() != 1)
		{
			
		}

		// combine trees in the forest into a single tree
		

		return forest.remove(forest.getKeyIterator().next()); // The final tree
	}

	/** Get the frequency of the characters */
	public static int[] getCharacterFrequency(String filename) 
	{    
		int counter = 0;
		int[] charCounts = new int[256];
		for(int i = 0; i < filename.length(); ++i)
		{
			for(int j = 0; j < filename.length(); ++j)
			{
				if(filename.charAt(j) == filename.charAt(i))
				{
					++counter;
				}
			}

			charCounts[(int) filename.charAt(i)] = counter;
		}

		return charCounts;

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
