package com.agileidc.itw.helper;

import fj.F; import fj.F2;
import fj.data.Option;
import fj.data.Stream;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

import static fj.Function.curry;
import static fj.data.Option.none;  
import static fj.data.Option.some;  
import fj.data.List;
import static fj.data.Stream.stream;
import static fj.data.Option.isSome_;

public class FuntionalJavaDateHelper {

	F<String, F<String, Option<Date>>> parseDate =
			  curry(new F2<String, String, Option<Date>>() {
			    public Option<Date> f(String pattern, String s) {
			      try {
			        return some(new SimpleDateFormat(pattern).parse(s));
			      }
			      catch (ParseException e) {
			        return none();
			      }
			    }
			  });
	
	/*public Option<Date> parseWithPatterns(String s, Stream<String> patterns) { 
		  return stream(s).apply(patterns.map(parseDate)).find(isSome_()); 
		}*/
	
}
