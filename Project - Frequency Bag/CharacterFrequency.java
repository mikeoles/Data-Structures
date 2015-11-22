import java.io.*;
public class CharacterFrequency
{
	public static void main(String[] Args)throws Exception
	{
		BufferedReader in = new BufferedReader(new FileReader(new File("letter1.txt")));
		FrequencyBag<Character> fb = new FrequencyBag<Character>();
		
		while(in.ready())
		{
			Character letter = (char)in.read();
			letter = Character.toLowerCase(letter);
			if(Character.isLetter(letter))
			{
				fb.add(letter);
			}
		}
		
		for(char a = 'a'; a<='z';a++) {
			System.out.println(a+": " + fb.getFrequencyOf(a));
		}
	}
}