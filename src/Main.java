import java.util.*;
import java.text.*;

public class Main
{
   public static void main (String[] args)
   {
      FloatStack fstk = new FloatStack();
      float f;

      fstk.pushFloat(1.1F);
      fstk.pushFloat(2.2F);
      fstk.pushFloat(3.3F);
      fstk.pushFloat(4.4F);

      for (int k = 0; k < 5; k++)
         if ( (f = fstk.popFloat()) != FloatStack.STACK_EMPTY)
            System.out.print( f + " ");
         else
            System.out.print("(empty stack) ");
      System.out.println();
   }
}

//Class FloatStack  ------------------------------------------------------------
class FloatStack extends Stack
{
   public static final float STACK_EMPTY = Float.MIN_VALUE;

   public void pushFloat(float x)
   {
      // don't allow pushing of Float.MIN_VALUE
      if (x == Float.MIN_VALUE)
         return;    // could throw an exception when we learn how
      // create a new FloatNode
      FloatNode fp = new FloatNode(x);

      // push the StackNode onto the stack (base class call)
      super.push(fp);
   }

   public float popFloat()
   {
      // pop a node
      FloatNode fp = (FloatNode)pop();
      if (fp == null)
         return STACK_EMPTY;
      else
         return fp.getData();
   }
}

//Class FloatNode  -------------------------------------------------------------
class FloatNode extends StackNode
{
   // additional data for subclass
   private float data;

   // constructor
   public FloatNode(float x)
   {
      super();  // constructor call to base class
      data = x;
   }

   // accessor
   public float getData()
   {
      return data;
   }

   // overriding show()
   public void show()
   {
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      System.out.print("[" + tidy.format(data) + "] ");
   }
}

//Class StackNode  -------------------------------------------------------------
class StackNode
{
   // data (we allow Stack class public access)
   protected StackNode next;

   // constructor
   public StackNode()
   {
      next = null;
   }

   // console display
   public void show()
   {
      System.out.print( "(generic node) ");
   }
}

//Class Stack ------------------------------------------------------------------
class Stack
{
   // pointer to first node in stack
   private StackNode top;

   // constructor
   public Stack()
   {
      top = null;
   }

   public void push(StackNode newNode)
   {
      if (newNode == null)
         return;   // emergency return
      newNode.next = top;
      top = newNode;
   }

   public StackNode pop()
   {
      StackNode temp;

      temp = top;
      if (top != null)
      {
         top = top.next;
         temp.next = null; // don't give client access to stack!
      }
      return temp;
   }

   // console display
   public void showStack()
   {
      StackNode p;

      // Display all the nodes in the stack
      for( p = top; p != null; p = p.next )
         p.show();
   }
}
