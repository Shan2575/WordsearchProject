/*
 * Brian Shanahan - 17218829
 * Daniel Clarke  - 18249736
 * Jessica Hoey   - 17201861
 * Martin Vaughan - 16158431
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordSearchPuzzle {
    public char[][] puzzle;
    public List<String> puzzleWords;

    public WordSearchPuzzle(List<String> userSpecifiedWords) {
        int longest = 0;

        for (int i = 0; i < userSpecifiedWords.size(); i++) {
            if (userSpecifiedWords.get(i).length() > longest) {
                longest = userSpecifiedWords.get(i).length();
            }
        }

        this.puzzle = new char[longest][longest];
        puzzleWords = userSpecifiedWords;
    }

    public WordSearchPuzzle(String wordFile, int wordCount, int shortest, int longest) {
        int temp;
        int count = 0;

        if (shortest > longest) {
            temp = longest;
            longest = shortest;
            shortest = temp;
        }

        puzzleWords = loadWordsFromFile(wordFile);
        List<String> chosen = new ArrayList<String>();

        for (int i = 0; i < wordCount; i++) {
            int pos = (int)((Math.random() * puzzleWords.size()));
            chosen.add(puzzleWords.get(pos));
        }

        for (int j = 0; j < chosen.size(); j++) {
            count = count + chosen.get(j).length();
        }

        count = (int)((Math.sqrt(count)) * 1.75);
        this.puzzle = new char[count][count];
        puzzleWords = chosen;        
    }

    private ArrayList<String> loadWordsFromFile(String filename) {
        try {
            FileReader aFileReader = new FileReader(filename);
            BufferedReader aBufferReader = new BufferedReader(aFileReader);
            String lineFromFile;
            ArrayList<String> words = new ArrayList<String>();
            lineFromFile = aBufferReader.readLine();
            while (lineFromFile != null) {
                words.add(lineFromFile.toUpperCase());
                lineFromFile = aBufferReader.readLine();
            }
            aBufferReader.close();
            aFileReader.close();
            return words;
        }
        catch (IOException x) {
            return null;
        }
    }

    public List<String> getWordSearchList() {
        return this.puzzleWords;
    }

    public char[][] getPuzzleAsGrid() {
        char[] a = {('q'),('w'),('e'),('r'),('t'),('y'),('u'),('i'),('o'),
                ('p'),('a'),('s'),('d'),('f'),('g'),('h'),('j'),('k'),
                ('l'),('z'),('x'),('c'),('v'),('b'),('n'),('m')};

        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[0].length; j++) {
                int z = (int)((Math.random()* a.length));
                puzzle[i][j] = a[z];
                System.out.print(puzzle[i][j] + ' ');
            }
            System.out.println();
        }

        return puzzle;
    }

    public String getPuzzleAsString() {
        String a = "";

        for (int col = 0; col < puzzle[0].length; col++) {
            int row = 0;

            for (row = 0; row < puzzle.length; row++) {
                a = a + (puzzle[row][col])+ ' ';
            }

            if (row == puzzle.length) {
                a = a +  '\n';
                row = 0;
            }
        }

        return a;
    }

    public void showWordSearchPuzzle(boolean hide) {
        generateWordSearchPuzzle();
        int i;
        int j;

        for (i = 0; i < puzzleWords.size(); i++) {
            System.out.println(puzzleWords.get(i));
        }

        System.out.println(getPuzzleAsString());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    private void generateWordSearchPuzzle() {
        int i = 0, rand, wordUsed = 0, row=0, col=0, k, empty = 0;
        puzzle = this.puzzle;
        while (i < puzzleWords.size()) {//while “i” is less than the size of the list of words
            //pick random number for direction
            //and random coordinate with row and col
            rand = (int)(Math.random()*4);
            row = (int)(Math.random()*(puzzle[0].length));
            col = (int)(Math.random()*(puzzle.length));
            //stores temp value of col/row so that it can be used to check for empty spaces in loop
            int coltemp = col ;
            int rowtemp = row ;
            //direction of word in puzzle is chosen according to value of random
            if(rand == 0)
            {
                // col/row+1 is done to take into count the actually pos of col/row in the grid
                //if length of puzzle – col/row+1 is greater or equal to length of chosen word then
                //there must be enough spaces for the word to fit
                if(puzzle[0].length - (col+1) >=puzzleWords.get(i).length())
                {

                    //next we check to see if there is enough EMPTY spaces to fit the word in
                    for(empty = 0 ; empty <puzzleWords.get(i).length(); empty++){
                        //if no letter is found we move on to check the next col
                        if((Character.isLetter(puzzle[row][coltemp]))==false){

                            coltemp++ ;

                        }else{
                            //if not we set empty to a value that will break the loop
                            //and that will not satisfy the next if statement
                            empty = puzzleWords.get(i).length()+1 ;

                        }
                    }
                    //here we check to see if there was indeed enough empty spaces
                    if(empty == puzzleWords.get(i).length())
                    {

                        k = 0 ;
                        //put in the characters of the chosen word one by one
                        while( k < puzzleWords.get(i).length() ){
                            puzzle[row][col] = puzzleWords.get(i).charAt(k) ;

                            col++ ;
                            k++ ;

                        }
                        //increment i to move onto the next chosen word
                        i++ ;
                    }

                }
            } //same method as above for checking empty spaces
            else if(rand == 1){
                if(puzzle.length - (col+1)>=puzzleWords.get(i).length() )
                {
                    for(empty = 0 ; empty <puzzleWords.get(i).length(); empty++){
                        if((Character.isLetter(puzzle[row][coltemp]))==false){

                            coltemp++ ;

                        }else{
                            empty = puzzleWords.get(i).length()+1 ;

                        }
                    }
                    if(empty == puzzleWords.get(i).length())
                    {
                        //same as above except we are putting characters in the word from last till first
                        k = puzzleWords.get(i).length()-1 ;
                        while( k >= 0 ){

                            puzzle[row][col] = puzzleWords.get(i).charAt(k) ;

                            col++;
                            k-- ;
                        }
                        // move onto next word
                        i++ ;
                    }

                }

            }else if(rand == 2)
            {//same as above except with changing rows
                //so the chosen words will be put in in a north or south direction

                if(puzzle[0].length - (row+1) >=puzzleWords.get(i).length() && (Character.isLetter(puzzle[row][col])==false))
                {
                    for(empty = 0 ; empty <puzzleWords.get(i).length(); empty++){
                        if((Character.isLetter(puzzle[rowtemp][col]))==false){

                            rowtemp++ ;

                        }else{
                            empty = puzzleWords.get(i).length()+1 ;

                        }
                    }
                    if(empty == puzzleWords.get(i).length()){

                        k = puzzleWords.get(i).length()-1 ;
                        while( k >= 0 ){

                            puzzle[row][col] = puzzleWords.get(i).charAt(k) ;

                            row++;
                            k-- ;

                        }
                        i++ ;
                    }

                }
            }

            else if(rand == 3)
            {//same as above
                if(puzzle[0].length - (row+1) >=puzzleWords.get(i).length() )
                {
                    for(empty = 0 ; empty <puzzleWords.get(i).length(); empty++){
                        if((Character.isLetter(puzzle[rowtemp][col]))==false){

                            rowtemp++ ;

                        }else{
                            empty = puzzleWords.get(i).length()+1 ;

                        }
                    }
                    if(empty == puzzleWords.get(i).length()){
                        k = 0 ;
                        while(k < puzzleWords.get(i).length() ){

                            puzzle[row][col] = puzzleWords.get(i).charAt(k) ;

                            row++;
                            k++ ;

                        }
                        i++ ;
                    }

                }
            }
        }

        int a,j;
        //characters of alphabet in order of how they appear on the keyboard
        char[] alphabet = {('Q'),('W'),('E'),('R'),('T'),('Y'),('U'),('I'),('O'),('P'),('A'),('S'),('D'),('F'),('G'),('H'),('J'),
                ('K'),('L'),('Z'),('X'),('C'),('V'),('B'),('N'),('M')} ;
        for(i=0;i < puzzleWords.size(); i++){
            //goes through the grid and if no character is in place it randomly adds one from the alphabet
            for(a=0; a<puzzle.length; a++)
            {
                for(j=0; j<puzzle[0].length; j++)
                {
                    if(Character.isLetter(puzzle[a][j])==false){
                        int z = (int)((Math.random()* alphabet.length));
                        puzzle[a][j] = alphabet[z] ;

                    }

                }
            }

        }

    }

}

    
    