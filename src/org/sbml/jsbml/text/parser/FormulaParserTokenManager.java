/* Generated By:JavaCC: Do not edit this line. FormulaParserTokenManager.java */
package org.sbml.jsbml.text.parser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.ASTNode.Type;
import org.sbml.jsbml.resources.Resource;

/** Token Manager. */
public class FormulaParserTokenManager implements FormulaParserConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x2000L) != 0L)
         {
            jjmatchedKind = 22;
            return 60;
         }
         if ((active0 & 0x1000L) != 0L)
            return 11;
         if ((active0 & 0x200L) != 0L)
            return 7;
         return -1;
      case 1:
         if ((active0 & 0x2000L) != 0L)
         {
            jjmatchedKind = 22;
            jjmatchedPos = 1;
            return 60;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 33:
         return jjStartNfaWithStates_0(0, 12, 11);
      case 40:
         return jjStopAtPos(0, 14);
      case 41:
         return jjStopAtPos(0, 15);
      case 42:
         return jjStopAtPos(0, 10);
      case 43:
         return jjStopAtPos(0, 7);
      case 45:
         return jjStartNfaWithStates_0(0, 9, 7);
      case 47:
         return jjStopAtPos(0, 11);
      case 94:
         return jjStopAtPos(0, 8);
      case 108:
         return jjMoveStringLiteralDfa1_0(0x2000L);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x2000L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 103:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(2, 13, 60);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 60;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 3)
                        kind = 3;
                     jjCheckNAddStates(0, 7);
                  }
                  else if ((0x7000000200000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 11;
                  else if ((0x2400L & l) != 0L)
                  {
                     if (kind > 23)
                        kind = 23;
                  }
                  else if (curChar == 46)
                     jjCheckNAddTwoStates(31, 8);
                  else if (curChar == 44)
                  {
                     if (kind > 6)
                        kind = 6;
                  }
                  else if (curChar == 45)
                     jjCheckNAddTwoStates(1, 7);
                  if ((0x7000000000000000L & l) != 0L)
                  {
                     if (kind > 16)
                        kind = 16;
                  }
                  break;
               case 7:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(8, 10);
                  else if (curChar == 46)
                     jjCheckNAdd(8);
                  break;
               case 60:
               case 16:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 22)
                     kind = 22;
                  jjCheckNAdd(16);
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(8, 10);
                  break;
               case 2:
                  if (curChar == 46)
                     jjCheckNAdd(3);
                  break;
               case 3:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(3, 4);
                  break;
               case 5:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(6);
                  break;
               case 6:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 5)
                     kind = 5;
                  jjCheckNAdd(6);
                  break;
               case 8:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(8, 4);
                  break;
               case 9:
                  if (curChar == 44)
                     kind = 6;
                  break;
               case 10:
                  if ((0x7000000200000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 11;
                  break;
               case 11:
                  if ((0x7000000000000000L & l) != 0L && kind > 16)
                     kind = 16;
                  break;
               case 17:
                  if ((0x2400L & l) != 0L)
                     kind = 23;
                  break;
               case 22:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 3)
                     kind = 3;
                  jjCheckNAddStates(0, 7);
                  break;
               case 23:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 3)
                     kind = 3;
                  jjCheckNAdd(23);
                  break;
               case 24:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 4)
                     kind = 4;
                  jjCheckNAddTwoStates(24, 25);
                  break;
               case 25:
                  if (curChar == 46)
                     jjCheckNAdd(26);
                  break;
               case 26:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 4)
                     kind = 4;
                  jjCheckNAdd(26);
                  break;
               case 27:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(27, 28);
                  break;
               case 29:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 22)
                     kind = 22;
                  jjstateSet[jjnewStateCnt++] = 29;
                  break;
               case 30:
                  if (curChar == 46)
                     jjCheckNAddTwoStates(31, 8);
                  break;
               case 31:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 4)
                     kind = 4;
                  jjCheckNAdd(31);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 22)
                        kind = 22;
                     jjCheckNAddTwoStates(15, 16);
                  }
                  if (curChar == 65)
                     jjAddStates(11, 14);
                  else if (curChar == 79)
                     jjCheckNAddStates(15, 18);
                  else if (curChar == 88)
                     jjAddStates(19, 22);
                  else if (curChar == 120)
                     jjAddStates(23, 24);
                  else if (curChar == 111)
                     jjCheckNAddTwoStates(38, 39);
                  else if (curChar == 97)
                     jjAddStates(25, 26);
                  else if (curChar == 78)
                     jjAddStates(27, 28);
                  else if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 13;
                  break;
               case 60:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 22)
                        kind = 22;
                     jjCheckNAdd(16);
                  }
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 22)
                        kind = 22;
                     jjCheckNAddTwoStates(15, 16);
                  }
                  break;
               case 4:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(29, 30);
                  break;
               case 12:
                  if (curChar == 116 && kind > 21)
                     kind = 21;
                  break;
               case 13:
               case 21:
                  if (curChar == 111)
                     jjCheckNAdd(12);
                  break;
               case 14:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 13;
                  break;
               case 15:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 22)
                     kind = 22;
                  jjCheckNAddTwoStates(15, 16);
                  break;
               case 16:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 22)
                     kind = 22;
                  jjCheckNAdd(16);
                  break;
               case 18:
                  if (curChar == 78)
                     jjAddStates(27, 28);
                  break;
               case 19:
                  if (curChar == 84 && kind > 21)
                     kind = 21;
                  break;
               case 20:
                  if (curChar == 79)
                     jjstateSet[jjnewStateCnt++] = 19;
                  break;
               case 28:
               case 29:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 22)
                     kind = 22;
                  jjCheckNAdd(29);
                  break;
               case 32:
                  if (curChar == 97)
                     jjAddStates(25, 26);
                  break;
               case 33:
                  if (curChar == 100 && kind > 17)
                     kind = 17;
                  break;
               case 34:
               case 56:
                  if (curChar == 110)
                     jjCheckNAdd(33);
                  break;
               case 35:
                  if (curChar == 100 && kind > 18)
                     kind = 18;
                  break;
               case 36:
               case 57:
                  if (curChar == 110)
                     jjCheckNAdd(35);
                  break;
               case 37:
                  if (curChar == 111)
                     jjCheckNAddTwoStates(38, 39);
                  break;
               case 38:
                  if (curChar == 114 && kind > 17)
                     kind = 17;
                  break;
               case 39:
                  if (curChar == 114 && kind > 19)
                     kind = 19;
                  break;
               case 40:
                  if (curChar == 120)
                     jjAddStates(23, 24);
                  break;
               case 41:
               case 45:
                  if (curChar == 111)
                     jjCheckNAdd(38);
                  break;
               case 42:
                  if (curChar == 114 && kind > 20)
                     kind = 20;
                  break;
               case 43:
               case 50:
                  if (curChar == 111)
                     jjCheckNAdd(42);
                  break;
               case 44:
                  if (curChar == 88)
                     jjAddStates(19, 22);
                  break;
               case 46:
                  if (curChar == 82 && kind > 17)
                     kind = 17;
                  break;
               case 47:
                  if (curChar == 79)
                     jjCheckNAdd(46);
                  break;
               case 48:
                  if (curChar == 82 && kind > 20)
                     kind = 20;
                  break;
               case 49:
                  if (curChar == 79)
                     jjstateSet[jjnewStateCnt++] = 48;
                  break;
               case 51:
                  if (curChar == 79)
                     jjCheckNAddStates(15, 18);
                  break;
               case 52:
                  if (curChar == 82 && kind > 19)
                     kind = 19;
                  break;
               case 53:
                  if (curChar == 65)
                     jjAddStates(11, 14);
                  break;
               case 54:
                  if (curChar == 68 && kind > 17)
                     kind = 17;
                  break;
               case 55:
                  if (curChar == 78)
                     jjstateSet[jjnewStateCnt++] = 54;
                  break;
               case 58:
                  if (curChar == 68 && kind > 18)
                     kind = 18;
                  break;
               case 59:
                  if (curChar == 78)
                     jjstateSet[jjnewStateCnt++] = 58;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 60 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   23, 24, 25, 1, 2, 4, 27, 28, 1, 2, 4, 55, 56, 57, 59, 38, 
   46, 52, 39, 45, 47, 49, 50, 41, 43, 34, 36, 20, 21, 5, 6, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, "\53", "\136", "\55", "\52", "\57", 
"\41", "\154\157\147", "\50", "\51", null, null, null, null, null, null, null, null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0xfffff9L, 
};
static final long[] jjtoSkip = {
   0x6L, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[60];
private final int[] jjstateSet = new int[120];
protected char curChar;
/** Constructor. */
public FormulaParserTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public FormulaParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 60; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100000200L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
