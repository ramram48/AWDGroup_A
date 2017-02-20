package uk.ac.port.SUMS.kernel.infrastructure;
import java.text.*;
import java.util.regex.*;

/**
@author Reciprocal
Ensures strings are in a consistent format, containing no invalid or strange characters,
such as null, control, formatting, and private use characters,
as well as any potential unpaired UTF-16 surrogates.
Note that tab characters count as strange characters.
*/
public class StringSanitizer{
 public StringSanitizer(){}
 
 public String ProcessLine(String Value){
  if(Value==null){return null;}
  return
   NewlineCharacters.matcher(
    Process(Value)
   ).replaceAll(" ")
  ;
 }
 
 public String ProcessParagraph(String Value){
  if(Value==null){return null;}
  return
   LegacyNewlineCharacters.matcher(
    Process(Value)
   ).replaceAll("\u2029")
  ;
 }
 
 protected String Process(String Value){
  return
   BadCharacters.matcher(
    Normalizer.normalize(Value,Normalizer.Form.NFD)
   ).replaceAll("")
  ;
 }
 
 static protected final Pattern BadCharacters=Pattern.compile("[\\x00-\\x09\\x0E-\\x1F\\x7F\\u0080-\\u009F\\p{Cs}\\p{Cn}\\p{Co}\\p{Cf}]");
 static protected final Pattern NewlineCharacters=Pattern.compile("\\r\\n|[\\x0A-\\x0D\\p{Zl}\\p{Zp}]");
 static protected final Pattern LegacyNewlineCharacters=Pattern.compile("\\r\\n|[\\x0A-\\x0D]");
}