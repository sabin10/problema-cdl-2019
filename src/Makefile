GCC=javac
CFLAGS=-std=c++11

build: Main.class

Main.class: Main.java
	$(GCC) $^

clean:
	rm -rf *.class

run: Main.class
	java Main $(var) $1