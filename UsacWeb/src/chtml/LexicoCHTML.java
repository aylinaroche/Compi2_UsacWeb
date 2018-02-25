/* The following code was generated by JFlex 1.6.1 */

package chtml;
import java_cup.runtime.Symbol;
import usacweb.Errores;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>C:/Users/Aroche/Documents/NetBeansProjects/UsacWeb/src/chtml/LexicoCHTML.jflex</tt>
 */
public class LexicoCHTML implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\53\1\41\1\61\1\2\1\44\22\0\1\53\1\0\1\40"+
    "\4\0\1\42\1\57\1\60\1\42\1\42\1\42\1\14\1\51\1\45"+
    "\1\37\11\37\1\42\1\52\1\1\1\50\1\10\1\0\1\42\1\16"+
    "\1\17\1\3\1\21\1\15\1\11\1\31\1\4\1\34\1\26\1\55"+
    "\1\7\1\6\1\13\1\22\1\25\1\33\1\24\1\35\1\5\1\23"+
    "\1\56\1\33\1\30\1\33\1\20\1\0\1\42\2\0\1\32\1\0"+
    "\1\16\1\17\1\3\1\21\1\15\1\11\1\31\1\4\1\34\1\26"+
    "\1\55\1\7\1\6\1\13\1\22\1\25\1\33\1\24\1\35\1\5"+
    "\1\23\1\56\1\33\1\30\1\33\1\20\1\42\1\43\1\42\7\0"+
    "\1\61\73\0\1\36\7\0\1\36\3\0\1\36\5\0\1\36\6\0"+
    "\1\36\6\0\1\36\7\0\1\36\3\0\1\36\5\0\1\36\6\0"+
    "\1\36\65\0\2\12\115\0\1\27\u1e9c\0\1\46\1\47\12\0\1\61"+
    "\1\61\u0100\0\1\54\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udee5\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\2\3\1\4\1\3\1\1\4\3"+
    "\1\5\1\1\1\6\1\1\1\7\1\10\1\3\1\11"+
    "\1\12\13\0\1\3\3\0\2\3\1\13\4\3\1\13"+
    "\2\0\1\14\2\0\1\14\1\3\2\0\1\15\1\0"+
    "\1\16\17\0\1\3\1\0\1\17\2\0\3\3\1\0"+
    "\4\3\1\20\1\3\4\0\1\21\20\0\1\3\3\0"+
    "\1\3\1\22\1\0\2\3\1\23\2\3\1\24\1\0"+
    "\1\25\20\0\2\26\10\0\1\27\1\0\1\3\1\30"+
    "\1\31\1\32\4\0\1\33\1\34\11\0\1\35\1\0"+
    "\1\36\4\0\1\37\13\0\1\3\1\40\1\0\1\41"+
    "\1\42\1\0\1\43\11\0\1\44\1\0\1\45\1\46"+
    "\2\0\1\47\1\50\1\0\1\51\10\0\1\3\1\0"+
    "\1\52\13\0\1\53\10\0\2\54\5\0\1\55\17\0"+
    "\1\56\1\0\1\57\12\0\1\60\3\0\1\61\2\0"+
    "\1\62\1\63\1\0\1\64\1\65\1\0\1\66\1\0"+
    "\1\67\1\0\1\70\1\0\1\71\1\72\1\73\1\0"+
    "\1\74\1\0\1\75\1\0\1\76\1\77\5\0\1\100"+
    "\1\101";

  private static int [] zzUnpackAction() {
    int [] result = new int[317];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\62\0\144\0\226\0\310\0\372\0\u012c\0\u015e"+
    "\0\u0190\0\u01c2\0\u01f4\0\u0226\0\u0258\0\u028a\0\62\0\u02bc"+
    "\0\62\0\62\0\u02ee\0\62\0\62\0\u0320\0\u0352\0\u0384"+
    "\0\u03b6\0\u03e8\0\u041a\0\u044c\0\u047e\0\u04b0\0\u04e2\0\u0514"+
    "\0\u0546\0\372\0\u0578\0\u05aa\0\u05dc\0\u060e\0\62\0\u0640"+
    "\0\u0672\0\u06a4\0\u06d6\0\310\0\u0708\0\u028a\0\62\0\u02bc"+
    "\0\u073a\0\u02bc\0\u076c\0\u079e\0\u07d0\0\62\0\u0802\0\62"+
    "\0\u0834\0\u0866\0\u0898\0\u08ca\0\u08fc\0\u092e\0\u0960\0\u0992"+
    "\0\u09c4\0\u09f6\0\u0a28\0\u0a5a\0\u0a8c\0\u0abe\0\u0af0\0\u0b22"+
    "\0\u0b54\0\u0b86\0\u0bb8\0\u0bea\0\u0c1c\0\u0c4e\0\u0c80\0\u0cb2"+
    "\0\u0ce4\0\u0d16\0\u0d48\0\u0d7a\0\u0708\0\u0dac\0\u0dde\0\u0e10"+
    "\0\u0e42\0\u0e74\0\62\0\u0ea6\0\u0ed8\0\u0f0a\0\u0f3c\0\u0f6e"+
    "\0\u0fa0\0\u0fd2\0\u1004\0\u1036\0\u1068\0\u109a\0\u10cc\0\u10fe"+
    "\0\u1130\0\u1162\0\u1194\0\u11c6\0\u11f8\0\u0b86\0\u122a\0\u125c"+
    "\0\310\0\u128e\0\u12c0\0\u12f2\0\310\0\u1324\0\u1356\0\62"+
    "\0\u1388\0\u13ba\0\u13ec\0\u141e\0\u1450\0\u1482\0\u14b4\0\u14e6"+
    "\0\u1518\0\u154a\0\u157c\0\u15ae\0\u15e0\0\u1612\0\u1644\0\u1676"+
    "\0\u16a8\0\u16da\0\62\0\310\0\u170c\0\u173e\0\u1770\0\u17a2"+
    "\0\u17d4\0\u1806\0\u1838\0\u186a\0\310\0\u189c\0\u18ce\0\310"+
    "\0\310\0\310\0\u1900\0\u1932\0\u1964\0\u1996\0\u19c8\0\62"+
    "\0\u19fa\0\u1a2c\0\u1a5e\0\u1a90\0\u1ac2\0\u1af4\0\u1b26\0\u1b58"+
    "\0\u1b8a\0\62\0\u1bbc\0\62\0\u1bee\0\u1c20\0\u1c52\0\u1c84"+
    "\0\62\0\u1cb6\0\u1ce8\0\u1d1a\0\u1d4c\0\u1d7e\0\u1db0\0\u1de2"+
    "\0\u1e14\0\u1e46\0\u1e78\0\u1eaa\0\u1edc\0\62\0\u1f0e\0\62"+
    "\0\62\0\u1f40\0\62\0\u1f72\0\u1fa4\0\u1fd6\0\u2008\0\u203a"+
    "\0\u206c\0\u209e\0\u20d0\0\u2102\0\62\0\u2134\0\62\0\62"+
    "\0\u2166\0\u2198\0\62\0\62\0\u21ca\0\62\0\u21fc\0\u222e"+
    "\0\u2260\0\u2292\0\u22c4\0\u22f6\0\u2328\0\u235a\0\u238c\0\u23be"+
    "\0\62\0\u23f0\0\u2422\0\u2454\0\u2486\0\u24b8\0\u24ea\0\u251c"+
    "\0\u254e\0\u2580\0\u25b2\0\u25e4\0\62\0\u2616\0\u2648\0\u267a"+
    "\0\u26ac\0\u26de\0\u2710\0\u2742\0\u2774\0\62\0\310\0\u27a6"+
    "\0\u27d8\0\u280a\0\u283c\0\u286e\0\62\0\u28a0\0\u28d2\0\u2904"+
    "\0\u2936\0\u2968\0\u299a\0\u29cc\0\u29fe\0\u2a30\0\u2a62\0\u2a94"+
    "\0\u2ac6\0\u2af8\0\u2b2a\0\u2b5c\0\62\0\u2b8e\0\62\0\u2bc0"+
    "\0\u2bf2\0\u2c24\0\u2c56\0\u2c88\0\u2cba\0\u2cec\0\u2d1e\0\u2d50"+
    "\0\u2d82\0\62\0\u2db4\0\u2de6\0\u2e18\0\62\0\u2e4a\0\u2e7c"+
    "\0\62\0\62\0\u2eae\0\62\0\62\0\u2ee0\0\62\0\u2f12"+
    "\0\62\0\u2f44\0\62\0\u2f76\0\62\0\62\0\62\0\u2fa8"+
    "\0\62\0\u2fda\0\62\0\u300c\0\62\0\62\0\u303e\0\u3070"+
    "\0\u30a2\0\u30d4\0\u3106\0\62\0\62";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[317];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\0\1\4\4\5\1\6\1\7\1\10"+
    "\1\5\1\2\1\5\1\11\5\5\1\12\2\5\1\2"+
    "\1\5\1\13\2\5\1\14\2\5\1\15\1\16\1\17"+
    "\2\2\1\17\1\2\2\20\1\21\1\2\1\22\1\17"+
    "\1\2\1\5\1\23\1\24\1\25\65\0\1\26\1\27"+
    "\1\0\1\30\3\0\1\31\1\32\2\0\1\33\1\0"+
    "\1\34\2\0\1\35\2\0\1\36\1\0\1\37\4\0"+
    "\1\32\1\37\3\0\1\26\2\0\1\26\1\40\5\0"+
    "\1\26\11\0\4\5\1\41\1\0\1\5\1\0\1\5"+
    "\1\0\12\5\1\0\10\5\15\0\2\5\6\0\5\5"+
    "\1\0\1\5\1\0\1\5\1\0\12\5\1\0\10\5"+
    "\15\0\2\5\5\0\1\42\5\43\1\0\1\43\1\0"+
    "\14\43\1\0\2\43\1\0\3\43\1\0\1\43\1\0"+
    "\1\42\1\43\1\0\1\42\1\43\2\0\3\43\1\42"+
    "\1\0\2\43\6\0\5\5\1\0\1\5\1\44\1\5"+
    "\1\0\5\5\1\45\4\5\1\0\4\5\1\46\3\5"+
    "\15\0\2\5\24\0\1\47\43\0\4\5\1\50\1\0"+
    "\1\5\1\0\1\51\1\0\12\5\1\0\10\5\15\0"+
    "\2\5\6\0\5\5\1\0\1\5\1\0\1\5\1\0"+
    "\6\5\1\52\3\5\1\0\10\5\15\0\2\5\6\0"+
    "\5\5\1\0\1\5\1\0\1\5\1\0\7\5\1\53"+
    "\2\5\1\0\10\5\15\0\2\5\6\0\5\5\1\0"+
    "\1\5\1\0\1\5\1\0\4\5\1\54\5\5\1\0"+
    "\10\5\15\0\2\5\42\0\1\15\11\0\1\55\10\0"+
    "\40\56\1\57\21\56\40\60\2\61\1\60\2\61\2\60"+
    "\1\62\12\60\3\0\5\5\1\0\1\5\1\0\1\5"+
    "\1\0\1\5\1\63\10\5\1\0\10\5\15\0\2\5"+
    "\5\0\1\26\1\27\1\0\1\30\3\0\1\31\1\32"+
    "\2\0\1\33\1\0\1\34\2\0\1\35\2\0\1\36"+
    "\1\0\1\37\4\0\1\32\1\37\3\0\1\26\2\0"+
    "\1\26\6\0\1\26\11\0\1\64\1\65\1\66\10\0"+
    "\1\67\1\70\3\0\1\71\2\0\1\72\45\0\1\73"+
    "\2\0\1\74\1\75\15\0\1\73\37\0\1\76\21\0"+
    "\1\76\33\0\1\77\66\0\1\100\70\0\1\101\64\0"+
    "\1\102\52\0\1\103\61\0\1\104\6\0\1\105\101\0"+
    "\1\106\17\0\5\5\1\0\1\5\1\107\1\5\1\0"+
    "\12\5\1\0\4\5\1\110\3\5\15\0\2\5\3\0"+
    "\1\111\1\112\37\111\1\113\20\111\13\0\1\114\51\0"+
    "\5\5\1\0\1\5\1\0\1\115\1\0\12\5\1\0"+
    "\10\5\15\0\2\5\6\0\5\5\1\0\1\5\1\0"+
    "\1\116\1\0\12\5\1\0\10\5\15\0\2\5\6\0"+
    "\2\5\1\117\2\5\1\0\1\5\1\120\1\5\1\0"+
    "\12\5\1\0\4\5\1\121\3\5\15\0\2\5\6\0"+
    "\1\122\4\5\1\0\1\5\1\0\1\5\1\0\12\5"+
    "\1\0\10\5\15\0\2\5\6\0\2\5\1\123\2\5"+
    "\1\0\1\5\1\0\1\5\1\0\12\5\1\0\10\5"+
    "\15\0\2\5\6\0\5\5\1\0\1\5\1\0\1\5"+
    "\1\0\6\5\1\124\3\5\1\0\10\5\15\0\2\5"+
    "\42\0\1\125\22\0\47\61\1\57\12\61\3\0\4\5"+
    "\1\126\1\0\1\5\1\0\1\5\1\0\12\5\1\0"+
    "\10\5\15\0\2\5\32\0\1\127\5\0\1\127\31\0"+
    "\1\130\102\0\1\131\50\0\1\132\73\0\1\133\5\0"+
    "\1\133\31\0\1\134\104\0\1\135\50\0\1\136\51\0"+
    "\1\137\3\0\1\140\64\0\1\141\46\0\1\142\3\0"+
    "\1\143\57\0\1\144\57\0\1\145\71\0\1\146\55\0"+
    "\1\147\64\0\1\150\21\0\1\150\25\0\45\151\1\152"+
    "\14\151\3\0\1\153\61\0\1\154\4\5\1\0\1\5"+
    "\1\0\1\5\1\0\12\5\1\0\10\5\15\0\2\5"+
    "\3\0\1\111\1\112\37\111\1\155\20\111\1\156\1\112"+
    "\37\156\1\0\20\156\1\113\1\0\37\113\1\155\20\113"+
    "\14\0\1\157\50\0\5\5\1\0\1\5\1\0\1\5"+
    "\1\0\4\5\1\160\5\5\1\0\10\5\15\0\2\5"+
    "\6\0\5\5\1\0\1\5\1\0\1\5\1\157\12\5"+
    "\1\0\10\5\15\0\2\5\6\0\5\5\1\0\1\5"+
    "\1\0\1\5\1\0\5\5\1\161\4\5\1\0\10\5"+
    "\15\0\2\5\16\0\1\162\51\0\5\5\1\0\1\5"+
    "\1\0\1\163\1\0\12\5\1\0\10\5\15\0\2\5"+
    "\6\0\1\5\1\164\3\5\1\0\1\5\1\0\1\5"+
    "\1\0\12\5\1\0\10\5\15\0\2\5\6\0\5\5"+
    "\1\0\1\5\1\0\1\5\1\0\1\5\1\165\10\5"+
    "\1\0\10\5\15\0\2\5\6\0\5\5\1\0\1\5"+
    "\1\0\1\5\1\0\10\5\1\166\1\5\1\0\10\5"+
    "\15\0\2\5\6\0\5\5\1\0\1\5\1\0\1\5"+
    "\1\0\5\5\1\167\4\5\1\0\10\5\15\0\2\5"+
    "\32\0\1\170\5\0\1\170\32\0\1\171\71\0\1\172"+
    "\67\0\1\173\60\0\1\174\43\0\1\175\63\0\1\176"+
    "\104\0\1\177\43\0\1\200\76\0\1\201\46\0\1\202"+
    "\61\0\1\203\65\0\1\204\51\0\1\205\21\0\1\205"+
    "\42\0\1\206\51\0\1\207\67\0\1\210\46\0\45\151"+
    "\1\211\14\151\45\0\1\212\70\0\2\213\7\0\5\5"+
    "\1\0\1\5\1\0\1\5\1\0\12\5\1\0\10\5"+
    "\14\0\1\213\1\214\1\5\3\0\1\215\1\0\60\215"+
    "\3\0\1\216\1\0\1\217\4\0\1\220\2\0\1\221"+
    "\1\0\1\222\2\0\1\223\4\0\1\224\4\0\1\220"+
    "\1\224\27\0\5\5\1\0\1\5\1\0\1\5\1\0"+
    "\5\5\1\225\4\5\1\0\10\5\15\0\2\5\20\0"+
    "\1\226\47\0\5\5\1\0\1\5\1\0\1\5\1\0"+
    "\1\227\11\5\1\0\10\5\15\0\2\5\6\0\5\5"+
    "\1\0\1\5\1\0\1\5\1\0\5\5\1\230\4\5"+
    "\1\0\10\5\15\0\2\5\6\0\5\5\1\0\1\5"+
    "\1\0\1\5\1\0\5\5\1\231\4\5\1\0\10\5"+
    "\15\0\2\5\6\0\5\5\1\0\1\5\1\0\1\5"+
    "\1\0\7\5\1\232\2\5\1\0\10\5\15\0\2\5"+
    "\12\0\1\233\104\0\1\234\54\0\1\235\43\0\1\236"+
    "\74\0\1\237\55\0\1\240\50\0\1\241\57\0\1\242"+
    "\1\0\1\243\3\0\1\244\3\0\1\245\7\0\1\246"+
    "\51\0\1\247\63\0\1\250\45\0\1\251\71\0\1\252"+
    "\70\0\1\253\46\0\1\254\74\0\1\255\52\0\1\256"+
    "\46\0\45\151\1\257\14\151\45\0\1\260\14\0\1\215"+
    "\1\261\60\215\5\0\1\262\10\0\1\263\1\264\54\0"+
    "\1\265\2\0\1\266\16\0\1\265\33\0\1\267\66\0"+
    "\1\270\70\0\1\271\64\0\1\272\61\0\1\273\52\0"+
    "\1\274\46\0\5\5\1\0\1\5\1\0\1\5\1\0"+
    "\1\5\1\275\10\5\1\0\10\5\15\0\2\5\5\0"+
    "\1\233\5\0\1\276\30\0\1\233\2\0\1\233\6\0"+
    "\1\233\13\0\1\277\76\0\1\300\61\0\1\301\71\0"+
    "\1\302\31\0\1\241\5\0\1\303\30\0\1\241\2\0"+
    "\1\241\6\0\1\241\11\0\1\304\1\305\11\0\1\306"+
    "\4\0\1\307\2\0\1\310\51\0\1\311\55\0\1\312"+
    "\21\0\1\312\40\0\1\313\64\0\1\314\56\0\1\315"+
    "\63\0\1\316\61\0\1\317\57\0\1\320\62\0\1\321"+
    "\62\0\1\322\44\0\10\151\1\323\34\151\1\257\14\151"+
    "\10\0\1\323\34\0\1\260\16\0\1\262\5\0\1\324"+
    "\30\0\1\262\2\0\1\262\6\0\1\262\34\0\1\325"+
    "\35\0\1\264\5\0\1\326\30\0\1\264\2\0\1\264"+
    "\6\0\1\264\13\0\1\327\104\0\1\330\47\0\1\331"+
    "\52\0\1\332\57\0\1\333\57\0\1\334\70\0\1\335"+
    "\21\0\1\335\46\0\1\336\43\0\5\5\1\0\1\5"+
    "\1\0\1\5\1\0\4\5\1\337\5\5\1\0\10\5"+
    "\15\0\2\5\20\0\1\340\62\0\1\341\72\0\1\342"+
    "\5\0\1\342\31\0\1\343\102\0\1\344\50\0\1\345"+
    "\73\0\1\346\5\0\1\346\43\0\1\347\51\0\1\350"+
    "\55\0\1\351\71\0\1\352\66\0\1\353\52\0\1\354"+
    "\74\0\1\355\53\0\1\356\66\0\1\357\43\0\1\360"+
    "\105\0\1\361\46\0\1\362\65\0\1\363\51\0\1\364"+
    "\21\0\1\364\40\0\1\365\70\0\1\366\42\0\5\5"+
    "\1\0\1\5\1\0\1\5\1\0\5\5\1\367\4\5"+
    "\1\0\10\5\15\0\2\5\33\0\1\370\60\0\1\371"+
    "\5\0\1\371\32\0\1\372\71\0\1\373\67\0\1\374"+
    "\37\0\1\346\5\0\1\375\30\0\1\346\2\0\1\346"+
    "\6\0\1\346\15\0\1\376\104\0\1\377\45\0\1\u0100"+
    "\60\0\1\u0101\62\0\1\u0102\55\0\1\u0103\21\0\1\u0103"+
    "\57\0\1\u0104\36\0\1\u0105\74\0\1\u0106\54\0\1\u0107"+
    "\47\0\1\u0108\71\0\1\u0109\70\0\1\u010a\52\0\1\u010b"+
    "\53\0\1\u010c\56\0\1\371\5\0\1\u010d\30\0\1\371"+
    "\2\0\1\371\6\0\1\371\15\0\1\u010e\54\0\1\373"+
    "\5\0\1\u010f\30\0\1\373\2\0\1\373\6\0\1\373"+
    "\33\0\1\u0110\52\0\1\u0111\50\0\1\u0112\73\0\1\u0113"+
    "\51\0\1\u0114\73\0\1\u0115\53\0\1\u0116\53\0\1\u0117"+
    "\76\0\1\u0118\41\0\1\u0119\5\0\1\u011a\21\0\1\u011b"+
    "\6\0\1\u0119\2\0\1\u0119\6\0\1\u0119\21\0\1\u011c"+
    "\63\0\1\u011d\46\0\1\u0109\5\0\1\u011e\30\0\1\u0109"+
    "\2\0\1\u0109\6\0\1\u0109\21\0\1\u011f\63\0\1\u0120"+
    "\66\0\1\u0121\41\0\1\u010e\5\0\1\u0122\30\0\1\u010e"+
    "\2\0\1\u010e\6\0\1\u010e\30\0\1\u0123\41\0\1\u0111"+
    "\5\0\1\u0124\30\0\1\u0111\2\0\1\u0111\6\0\1\u0111"+
    "\10\0\1\u0112\5\0\1\u0125\30\0\1\u0112\2\0\1\u0112"+
    "\6\0\1\u0112\23\0\1\u0126\46\0\1\u0114\5\0\1\u0127"+
    "\30\0\1\u0114\2\0\1\u0114\6\0\1\u0114\30\0\1\u0128"+
    "\41\0\1\u0116\5\0\1\u0129\30\0\1\u0116\2\0\1\u0116"+
    "\6\0\1\u0116\23\0\1\u012a\46\0\1\u0118\5\0\1\u012b"+
    "\30\0\1\u0118\2\0\1\u0118\6\0\1\u0118\10\0\1\u0119"+
    "\5\0\1\u011a\30\0\1\u0119\2\0\1\u0119\6\0\1\u0119"+
    "\24\0\1\u012c\45\0\1\u011c\5\0\1\u012d\30\0\1\u011c"+
    "\2\0\1\u011c\6\0\1\u011c\10\0\1\u011d\5\0\1\u012e"+
    "\30\0\1\u011d\2\0\1\u011d\6\0\1\u011d\10\0\1\u011f"+
    "\5\0\1\u012f\30\0\1\u011f\2\0\1\u011f\6\0\1\u011f"+
    "\32\0\1\u0130\37\0\1\u0123\5\0\1\u0131\30\0\1\u0123"+
    "\2\0\1\u0123\6\0\1\u0123\26\0\1\u0132\43\0\1\u0128"+
    "\5\0\1\u0133\30\0\1\u0128\2\0\1\u0128\6\0\1\u0128"+
    "\36\0\1\u0134\33\0\1\u012c\5\0\1\u0135\30\0\1\u012c"+
    "\2\0\1\u012c\6\0\1\u012c\10\0\1\u0130\5\0\1\u0136"+
    "\30\0\1\u0130\2\0\1\u0130\6\0\1\u0130\24\0\1\u0137"+
    "\50\0\1\u0138\75\0\1\u0139\62\0\1\u013a\61\0\1\u013b"+
    "\41\0\1\u013a\5\0\1\u013c\30\0\1\u013a\2\0\1\u013a"+
    "\6\0\1\u013a\10\0\1\u013b\5\0\1\u013d\30\0\1\u013b"+
    "\2\0\1\u013b\6\0\1\u013b\6\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[12600];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\14\1\1\11\1\1\2\11\1\1\2\11"+
    "\13\0\1\1\3\0\2\1\1\11\5\1\2\0\1\11"+
    "\2\0\2\1\2\0\1\11\1\0\1\11\17\0\1\1"+
    "\1\0\1\1\2\0\3\1\1\0\6\1\4\0\1\11"+
    "\20\0\1\1\3\0\2\1\1\0\5\1\1\11\1\0"+
    "\1\1\20\0\1\11\1\1\10\0\1\1\1\0\4\1"+
    "\4\0\1\1\1\11\11\0\1\11\1\0\1\11\4\0"+
    "\1\11\13\0\1\1\1\11\1\0\2\11\1\0\1\11"+
    "\11\0\1\11\1\0\2\11\2\0\2\11\1\0\1\11"+
    "\10\0\1\1\1\0\1\11\13\0\1\11\10\0\1\11"+
    "\1\1\5\0\1\11\17\0\1\11\1\0\1\11\12\0"+
    "\1\11\3\0\1\11\2\0\2\11\1\0\2\11\1\0"+
    "\1\11\1\0\1\11\1\0\1\11\1\0\3\11\1\0"+
    "\1\11\1\0\1\11\1\0\2\11\5\0\2\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[317];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
public String lexeme;



  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public LexicoCHTML(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 280) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(sym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { Errores.agregarError("Error Lexico",yytext(), yyline,yycolumn);
            }
          case 66: break;
          case 2: 
            { return new Symbol(sym.menor, yycolumn, yyline,new String(yytext()));
            }
          case 67: break;
          case 3: 
            { lexeme=yytext(); return new Symbol(sym.id, yycolumn, yyline,new String(yytext()));
            }
          case 68: break;
          case 4: 
            { return new Symbol(sym.mayor, yycolumn, yyline,new String(yytext()));
            }
          case 69: break;
          case 5: 
            { lexeme=yytext(); return new Symbol(sym.entero, yycolumn, yyline,new String(yytext()));
            }
          case 70: break;
          case 6: 
            { /*Ignore*/
            }
          case 71: break;
          case 7: 
            { return new Symbol(sym.igual, yycolumn, yyline,new String(yytext()));
            }
          case 72: break;
          case 8: 
            { return new Symbol(sym.puntoComa, yycolumn, yyline,new String(yytext()));
            }
          case 73: break;
          case 9: 
            { return new Symbol(sym.parentesisA, yycolumn, yyline,new String(yytext()));
            }
          case 74: break;
          case 10: 
            { return new Symbol(sym.parentesisC, yycolumn, yyline,new String(yytext()));
            }
          case 75: break;
          case 11: 
            { return new Symbol(sym.idd, yycolumn, yyline,new String(yytext()));
            }
          case 76: break;
          case 12: 
            { lexeme=yytext(); return new Symbol(sym.cadena ,yycolumn, yyline,new String(yytext()));
            }
          case 77: break;
          case 13: 
            { return new Symbol(sym.ct, yychar, yyline, yytext());
            }
          case 78: break;
          case 14: 
            { return new Symbol(sym.cb, yychar, yyline, yytext());
            }
          case 79: break;
          case 15: 
            { lexeme=yytext(); return new Symbol(sym.cadenasin ,yycolumn, yyline,new String(yytext()));
            }
          case 80: break;
          case 16: 
            { lexeme=yytext(); return new Symbol(sym.decimal, yycolumn, yyline,new String(yytext()));
            }
          case 81: break;
          case 17: 
            { return new Symbol(sym.cjs, yychar, yyline, yytext());
            }
          case 82: break;
          case 18: 
            { return new Symbol(sym.alto, yycolumn, yyline,new String(yytext()));
            }
          case 83: break;
          case 19: 
            { return new Symbol(sym.ruta, yycolumn, yyline, new String(yytext()));
            }
          case 84: break;
          case 20: 
            { return new Symbol(sym.ccss, yychar, yyline, yytext());
            }
          case 85: break;
          case 21: 
            { return new Symbol(sym.caja, yychar, yyline, yytext());
            }
          case 86: break;
          case 22: 
            { return new Symbol(sym.click, yycolumn, yyline, new String(yytext()));
            }
          case 87: break;
          case 23: 
            { return new Symbol(sym.fondo, yycolumn, yyline, new String(yytext()));
            }
          case 88: break;
          case 24: 
            { return new Symbol(sym.ancho, yycolumn, yyline,new String(yytext()));
            }
          case 89: break;
          case 25: 
            { return new Symbol(sym.grupo, yycolumn, yyline,new String(yytext()));
            }
          case 90: break;
          case 26: 
            { return new Symbol(sym.valor, yycolumn, yyline,new String(yytext()));
            }
          case 91: break;
          case 27: 
            { return new Symbol(sym.texto, yychar, yyline, yytext());
            }
          case 92: break;
          case 28: 
            { return new Symbol(sym.tabla, yychar, yyline, yytext());
            }
          case 93: break;
          case 29: 
            { return new Symbol(sym.boton, yychar, yyline, yytext());
            }
          case 94: break;
          case 30: 
            { return new Symbol(sym.panel, yychar, yyline, yytext());
            }
          case 95: break;
          case 31: 
            { lexeme=yytext(); return new Symbol(sym.cadenacon, yychar, yyline, yytext());
            }
          case 96: break;
          case 32: 
            { return new Symbol(sym.chtml, yychar, yyline, yytext());
            }
          case 97: break;
          case 33: 
            { return new Symbol(sym.cuerpo, yychar, yyline, yytext());
            }
          case 98: break;
          case 34: 
            { return new Symbol(sym.titulo, yychar, yyline, yytext());
            }
          case 99: break;
          case 35: 
            { return new Symbol(sym.filt, yychar, yyline, yytext());
            }
          case 100: break;
          case 36: 
            { return new Symbol(sym.imagen, yychar, yyline, yytext());
            }
          case 101: break;
          case 37: 
            { return new Symbol(sym.enlace, yychar, yyline, yytext());
            }
          case 102: break;
          case 38: 
            { return new Symbol(sym.opcion, yychar, yyline, yytext());
            }
          case 103: break;
          case 39: 
            { 
            }
          case 104: break;
          case 40: 
            { return new Symbol(sym.finct, yychar, yyline, yytext());
            }
          case 105: break;
          case 41: 
            { return new Symbol(sym.fincb, yychar, yyline, yytext());
            }
          case 106: break;
          case 42: 
            { return new Symbol(sym.textoa, yychar, yyline, yytext());
            }
          case 107: break;
          case 43: 
            { return new Symbol(sym.spinner, yychar, yyline, yytext());
            }
          case 108: break;
          case 44: 
            { return new Symbol(sym.alineado, yycolumn, yyline,new String(yytext()));
            }
          case 109: break;
          case 45: 
            { return new Symbol(sym.fincjs, yychar, yyline, yytext());
            }
          case 110: break;
          case 46: 
            { return new Symbol(sym.finccss, yychar, yyline, yytext());
            }
          case 111: break;
          case 47: 
            { return new Symbol(sym.fincaja, yychar, yyline, yytext());
            }
          case 112: break;
          case 48: 
            { return new Symbol(sym.fintexto, yychar, yyline, yytext());
            }
          case 113: break;
          case 49: 
            { return new Symbol(sym.finboton, yychar, yyline, yytext());
            }
          case 114: break;
          case 50: 
            { return new Symbol(sym.cajatexto, yychar, yyline, yytext());
            }
          case 115: break;
          case 51: 
            { return new Symbol(sym.finchtml, yychar, yyline, yytext());
            }
          case 116: break;
          case 52: 
            { return new Symbol(sym.fintabla, yychar, yyline, yytext());
            }
          case 117: break;
          case 53: 
            { return new Symbol(sym.finfilt, yychar, yyline, yytext());
            }
          case 118: break;
          case 54: 
            { return new Symbol(sym.finpanel, yychar, yyline, yytext());
            }
          case 119: break;
          case 55: 
            { return new Symbol(sym.saltofin, yychar, yyline, yytext());
            }
          case 120: break;
          case 56: 
            { return new Symbol(sym.fintitulo, yychar, yyline, yytext());
            }
          case 121: break;
          case 57: 
            { return new Symbol(sym.finimagen, yychar, yyline, yytext());
            }
          case 122: break;
          case 58: 
            { return new Symbol(sym.finenlace, yychar, yyline, yytext());
            }
          case 123: break;
          case 59: 
            { return new Symbol(sym.finopcion, yychar, yyline, yytext());
            }
          case 124: break;
          case 60: 
            { return new Symbol(sym.fincuerpo, yychar, yyline, yytext());
            }
          case 125: break;
          case 61: 
            { return new Symbol(sym.encabezado, yychar, yyline, yytext());
            }
          case 126: break;
          case 62: 
            { return new Symbol(sym.fintextoa, yychar, yyline, yytext());
            }
          case 127: break;
          case 63: 
            { return new Symbol(sym.finspinner, yychar, yyline, yytext());
            }
          case 128: break;
          case 64: 
            { return new Symbol(sym.fincajatexto, yychar, yyline, yytext());
            }
          case 129: break;
          case 65: 
            { return new Symbol(sym.finencabezado, yychar, yyline, yytext());
            }
          case 130: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
