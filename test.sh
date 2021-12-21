export JAVA_HOME=/usr/bin/java
export PATH=$PATH:$JAVA_HOME/bin
export CLASSPATH=/home/sm/bin/java/jars/jsoup-1.14.3.jar
export JSOUP_HOME=/home/sm/bin/java/jars/
javac ./wikt_scrape_en.java
java ./wikt_scrape_en.java $@
