---

Lab Assistant Project - Web Crawling, Semantic Search and Knowledge Graph Construction
=========
## UCSD Super Computer Center



### Function 1: src/com/news/NEWS_ONEStep
1. Filter the original result.txt (1st url list) to delete unwanted links.
2. Pull all the news from the filtered-url-list and save into txt file (in html format)
3. Clean up the allnews.txt file (delete html tags) and generate clean_10FactNews.txt file
4. Get rid of all stop words and stem them! This will generate allkeywords_10Facts.txt file
5. Step five: Count top N words of each news piece and this will generate TopNwords_10Facts.txt file
(You can find all the outputs file in files named by dates, e.g. 2018-11-13)

Output 1 - TopNwords_10Facts.txt 
[![image](https://github.com/huangbeidan/SpiderNews/blob/master/assets/output11.png)](#capture)

### Function 2: src/com/corenlp/dict4.java
1. Read all news from "10Knewsarticles.csv"
2. Use Stanford NLP to do POST TAGING and analyze the structure
3. Keep Proper Nouns phrases and the original sentences while removing all stop words and stemming them
4. Write all the extracted and filtered NNPs to local disk file 

Output 2 - dictionary.csv 
[![image](https://github.com/huangbeidan/Spidernews/blob/master/assets/output22.png)](#capture)

### Function 3: src/graph/PGraph.java
1. Construct the TreeMap by reading from csv: Vertex + Sentence1/Sentence2/...
2. Inverted index to get string keys in an array
3. Build the graph by connecting first vertex on each

Output 3 - Graph result - each number represents a particular NNPs
[![image](https://github.com/huangbeidan/Spidernews/blob/master/assets/output33.png)](#capture)
