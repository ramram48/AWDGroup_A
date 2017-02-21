package uk.ac.port.SUMS.ProjectIdeas.test;
import java.text.*;
import java.util.*;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@RequestScoped @Named(value="Debug")
public class Debug{
 public void Execute(){
  String Result=MessageFormat.format("{0,time,full}",Calendar.getInstance().getTime());
  return;
 }
}
