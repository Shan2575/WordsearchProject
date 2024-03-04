public class WordSearchPuzzleDriver {
    public static void main(String[] args) {
        System.out.println();
        WordSearchPuzzle wordSearch = new WordSearchPuzzle("bncwords.txt", 5, 10, 8);
        
        wordSearch.getWordSearchList() ;//gets the list of chose word

        wordSearch.showWordSearchPuzzle(false) ;
        
        System.out.println();
        wordSearch.getPuzzleAsGrid();
        wordSearch.getPuzzleAsString();
    }
}
