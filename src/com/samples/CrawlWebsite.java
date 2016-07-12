/*
 * Copyright 2016 Michael Markidis
 * Licensed under the MIT.
 * 
 */

package com.samples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Given a root URL (e.g. http://www.domain.com/) crawl the website n levels
 * deep and collect the number of distinct links (hrefs) at the following
 * levels:
 * 
 * 1. The page level - How many distinct links does the page link to 
 * 2. The level - How many distinct links does all pages at the given depth link to
 * 3. The entire website - How many distinct links does the website link to after crawling n levels deep
 * 
 * @author Michael Markidis
 *
 */

public class CrawlWebsite
{
	// where to start the web crawling
	private final static String	 ROOT_URL = "http://www.signetarmorlite.com";

	// compile the pattern once and reuse it
	private final static Pattern PATTERN  = Pattern.compile("href=\"(" + ROOT_URL + ".*?)\"");

	public static void main(String[] args)
	{
		try
		{
			int numberOfLevels = 2;

			Map<String, Boolean> distinctURLsForWebsiteMap = new HashMap<>();

			// start off at the root
			distinctURLsForWebsiteMap.put(ROOT_URL, false);

			// for each level go through each distinct URL
			for (int i = 0; i < numberOfLevels; i++)
			{
				Map<String, Boolean> distinctURLsForLevelMap = new HashMap<>();

				System.out.println("\n--------------------");
				System.out.println("Level # " + (i + 1));
				System.out.println("--------------------");

				for (Map.Entry<String, Boolean> entry : distinctURLsForWebsiteMap.entrySet())
				{
					String currURL = entry.getKey();
					boolean visited = entry.getValue();
					if (!visited)
					{
						Map<String, Boolean> distinctURLsForPageMap = new HashMap<>();
						try
						{
							System.out.print("Visting: " + currURL);

							getDistinctURLsOnPage(distinctURLsForPageMap, entry.getKey());

							// print the results for this page
							printNumberOfDistinctAdded(distinctURLsForPageMap, 0);
						}
						catch (IOException ioe)
						{
							System.out.println(" [Couldn't connect to: " + currURL + "]");
						}
						finally
						{
							// mark this page as visited
							entry.setValue(true);

							// add from the page Map into the level map
							distinctURLsForPageMap.keySet().removeAll(distinctURLsForLevelMap.keySet());
							distinctURLsForLevelMap.putAll(distinctURLsForPageMap);
						}
					}
					else
					{
						System.out.println("Already visted: " + currURL);
					}
				}

				int beforeSize = distinctURLsForWebsiteMap.size();

				// add from the level Map into the website map
				distinctURLsForLevelMap.keySet().removeAll(distinctURLsForWebsiteMap.keySet());
				distinctURLsForWebsiteMap.putAll(distinctURLsForLevelMap);

				// print the results for this level
				printNumberOfDistinctAdded(distinctURLsForLevelMap, beforeSize, "Level " + (i + 1) + " ");

				// remove all mappings for the level
				distinctURLsForLevelMap.clear();
			}

			// Print out the website results for all levels
			System.out.println("---------------------");
			System.out.print("Crawling " + numberOfLevels + " deep results in ");
			System.out.print(distinctURLsForWebsiteMap.size() + " distinct URLs!");
		}
		catch (Throwable t)
		{
			t.printStackTrace(System.out);
		}
	}

	/**
	 * Scans through each line of HTML in the given page, point to by the passed
	 * in url, and keeps a map of the distinct set of URLs that this page links
	 * to.
	 * 
	 * <pre>
	 * <b>Note</b>: Links must be of the form href="[Same Absolute Path as Root]"
	 * 
	 * For example, if ROOT_URL = http://www.domain.com/ , then only the following links will be considered:
	 * http://www.domain.com/page1
	 * http://www.domain.com/dir/page2
	 * 
	 * </pre>
	 * 
	 * @param tempMap
	 *            - Map used to store the distinct set of URLs that linked to
	 *            from the passed in URL.
	 * @param url
	 *            - the URL to be read
	 * @throws IOException
	 *             if the URL cannot be accessed
	 */
	private static void getDistinctURLsOnPage(Map<String, Boolean> tempMap, final String url) throws IOException
	{
		BufferedReader br = null;
		try
		{
			URL uri = new URL(url);
			URLConnection conn = uri.openConnection();

			// open the stream and wrap it into a BufferedReader
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String inputLine;
			while ((inputLine = br.readLine()) != null)
			{
				// remove all tabs in this line of HTML
				inputLine = inputLine.trim();

				// matching will be case-insensitive
				inputLine = inputLine.toLowerCase();

				Matcher matcher = PATTERN.matcher(inputLine);
				while (matcher.find())
				{
					String hrefLink = matcher.group(1);
					tempMap.putIfAbsent(hrefLink, false);
				}
			}
		}
		finally
		{
			if (br != null)
			{
				br.close();
			}
		}
	}

	/**
	 * Prints out the number of distinct URL entries that have been added to
	 * this map.
	 * 
	 * <br>
	 * Same as calling: {@link #printNumberOfDistinctAdded(Map, int, String)
	 * printNumberOfDistinctAdded(map, beforeSize, "")}
	 * 
	 * @param map
	 *            - the map being evaluated.
	 * @param beforeSize
	 *            - the size of the map at some earlier point in time
	 */
	private static void printNumberOfDistinctAdded(Map<?, ?> map, int beforeSize)
	{
		printNumberOfDistinctAdded(map, beforeSize, "");
	}

	/**
	 * Prints out the number of distinct URL entries that have been added to
	 * this map.
	 * 
	 * @param map
	 *            - the map being evaluated.
	 * @param beforeSize
	 *            - the size of the map at some earlier point in time
	 * @param prefix
	 *            - any prefix to include in the print out
	 */
	private static void printNumberOfDistinctAdded(Map<?, ?> map, int beforeSize, String prefix)
	{
		int afterSize = map.size();
		System.out.println(" [" + prefix + "Links to: " + (afterSize - beforeSize) + " distinct URLs]");
	}
}
