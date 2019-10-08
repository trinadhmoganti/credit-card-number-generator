package com.example.springboot;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreditCardNumberGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardNumberGeneratorApplication.class, args);
		generateCCNumbers(10);
	}

	public static void generateCCNumbers(int count) {
		Instant start = Instant.now();

		long lowerLimit = 4000000000000000L;
		long upperLimit = 4999999999999999L;

		String[] numbers = new String[count];
		String fileName = new SimpleDateFormat("'CC-'yyyy-MM-dd-HH-mm-ss").format(new Date());

		try (BufferedWriter outputWriter = new BufferedWriter(new FileWriter(fileName))) {

			for (int i = 0; i < count; i++) {
				numbers[i] = ThreadLocalRandom.current().nextLong(lowerLimit, upperLimit) + "";
				outputWriter.write(numbers[i]);
				if (i != count - 1)
					outputWriter.newLine();
			}
			outputWriter.flush();
			Instant finish = Instant.now();
			long timeElapsed = Duration.between(start, finish).toMillis();
			System.out.println(timeElapsed + "ms");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
