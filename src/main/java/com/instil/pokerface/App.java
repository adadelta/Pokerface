package com.instil.pokerface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.Callable;

import com.instil.pokerface.model.PokerHand;
import com.instil.pokerface.utils.CardParser;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "Poker Face", descriptionHeading = "%nDescription:%n%n", 
		 description = "Converts specified poker hands hands into their names", 
		 parameterListHeading = "%nParameters:%n", optionListHeading = "%nOptions:%n", 
		 mixinStandardHelpOptions = true, version = "PokerFace 1.0.0", footer = "gulli.dev 2019")
public class App implements Callable<Void> {
	
	@Option(names = { "-f", "--file" }, description = "Path and name of file with poker hands", paramLabel = "FILE", required = true)
	private File file;

	public static void main(String[] args) {
		CommandLine.call(new App(), args);
	}
	
	/**
	 * Will handle reading each line of the file, parsing the hands and handling outputs
	 */
	@Override
	public Void call() throws Exception {

		if (this.file.canRead()) {
			
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {

				String line = null;
				while ((line = br.readLine()) != null) {
					PokerHand hand = CardParser.parseHand(line);
					System.out.println(line + " => " + hand.translateHand());
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Do not have read permission to read this file");
		}
		return null;
	}

}
