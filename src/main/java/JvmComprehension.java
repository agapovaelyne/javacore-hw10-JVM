//classLoader'ы  загружают классы JvmComprehension (Application CL) и системыне классы (Bootstrap CL) в MetaSpace,
//затем происходит связывание (Linking) ( verifying -> preparing -> resolving) , потом инициализация классов

public class JvmComprehension {
    public static void main(String[] args) { //создаётся фрейм main() в стеке
        int i = 1;                      // 1 - в фрейм main() в стеке записывается переменная примитивного типа int i
        Object o = new Object();        // 2 -  создаётся объект класса Object и записывается в кучу, а ссылка на него “o” записывается в стек во фрейм main()
        Integer ii = 2;                 // 3 - создаются объект класса Integer и ссылка на него - “ii“. Объект записывается в кучу, ссылка на него - в стек во фрейм main()
        printAll(o, i, ii);             // 4 - создается фрейм printAll() в стеке, в фрейм записываются переменная int i примитивного типа, а также ссылки "o" и "ii" на объекты Object и Integer из кучи, созданные в строках 2 и 3
        System.out.println("finished"); // 7 - по аналогии с описанием выполненения System.out.println(o.toString() + i + ii) ниже в комментариях
    }

    private static void printAll(Object o, int i, Integer ii) {
        Integer uselessVar = 700;                   // 5 - в куче создаётся объект класса Integer,а во фрейме printAll() - ссылка на него "uselessVar"
        System.out.println(o.toString() + i + ii);  /* 6 - создаются 2 фрейма в стеке:  и System.out.println(),
         в рамках выполнения o.toString():
            создается фрейм toString() в стеке
               создаются фреймы getClass() и getName(), в кучу (в String Pool) записывается объект String, а во фрейм getName() ссылка на этот объект - "name",
               создается фрейм toHexString(), в который записывается переменная примитивного типа int i, равная значению hashCode() объекта "о",
                   а затем создается фрейм toUnsignedString0() и в него записываются переменные-примитивы int val, int shift,
                       создаётся фрейм numberOfLeadingZeros() и в него записываются переменнае - примитивы int i,int n, также создаются фреймы для всех блоков if в рамках выполнения numberOfLeadingZeros(), в них записываются переменные int i и int n
                       в фрейм toUnsignedString0() записывается переменная примитивного типа int mag, затем создается фрейм max(), в который записываются переменные int a, int b,
                           создается фрейм в соответствие с веткой тренарного оператора, в который записываются переменные int a, int b
                       в фрейм toUnsignedString0() записывается переменная примитивного типа int chars, создается фрейм для блока if/else в рамках выполнения toUnsignedString0(),
                           в рамках выполнения блока if создается массив byte[] и кладётся в кучу, а ссылка на него "buf" кладется в соответствующий фрейм блока if вместе с переменными int val,int shift,int chars,
                           при этом в рамках выполнения блока if создается фрейм formatUnsignedInt(), в который записываются int val, int shift, ссылка buf на созданный ранее массив byte[], int len, int charPos, int radix, int mask.
                               cоздается фрейм для блока цикла do внутри formatUnsignedInt()  , в который записываются ссылка buf на созданный ранее массив byte[],int charPos, int mask, int val,int shift
                           в рамках выполнения блока else в методе toUnsignedString0() создается массив byte[] и кладётся в кучу, а ссылка на него "buf" кладется в соответствующий фрейм блока вместе с переменными int val,int shift,int chars,
                           при этом в рамках выполнения блока else создается фрейм formatUnsignedIntUTF16(), в который записываются int val, int shift, ссылка buf на созданный ранее массив byte[], int len, int charPos, int radix, int mask.
                               создается фрейм для блока цикла do внутри formatUnsignedIntUTF16(), в который записываются ссылка buf на созданный ранее массив byte[],int charPos, int mask, int val,int shift
                               создаётся фрейм putChar(), в него записываются ссылка на переданный в метод массив byte[] из кучи val, int index, int c,
                                   создаётся фрейм length(), в него записывается ссылка "value" на переданный в метод массив byte[]
        в рамках выполнения метода System.out.println(o.toString() + i + ii):
            в кучу (в String Pool) записывается объект String, а во фрейм System.out.println() ссылка на него - "x",
            создается фрейм для if/else.
            В рамках выполнения блока if:
                создаётся фрейм valueOf() в стеке и объект Object в куче, ссылка на объект "obj" кладётся в фрейм valueOf(),
                    в рамках выполнения метода valueOf() создается фрейм для тернарного оператора, в одном случае во фрейм кладётся ссылка "obj" на созданный выше объект, а в другом происходит выполнение obj.toString() и в память записывается всё, что описывалось ранее при описании o.toString() выше (строки 16-32)
                создаются фреймы writeln(), synchronized() со ссылкой на объект this и в куче (в String Pool) объект String, ссылка на него "s" кладется во фрейм, создается фрейм для try/catch,
                    в этом блоке также иерархично разворачиваются фреймы write() с ссылкой s на объект String в куче (в String Pool), length(), newLine() , lineSeparator(), flushBuffer() + фрейм для if внутри этого метода, flush() или interrupt() + 2 фрейма для if и interrupt0() + currentThread()+ checkAccess() с фреймом для if, currentThread(),... в зависимости от хода выполнения,  в рамках которого в указанные фреймы и в кучу записываются соответствующие данные
            В рамках выполнения блока else:
                создаётся фрейм synchronized(), в который кладется ссылка на объект "this" и ссылка "x" на объект String, переданный в System.out.println()
                    создаётся фрейм print(), в него записывается ссылка "s" на переданный в метод print() объект String == объект String, переданный в System.out.println(),
                        создается фрейм valueOf(), в который записывается ссылка "obj" на переданный в метод valueOf() объект == объект String, переданный в System.out.println(),
                            создается фрейм для одной из веток тернарного оператора внутри valueOf(), в одной из веток создаются и записываются ссылка "obj" на объект переданный в метод,а также все данные, что описывались ранее при описании o.toString() (строки 16-32)
                        создается фрейм write() с ссылкой s на объект String в куче (в String Pool), length(), newLine() , lineSeparator(), flushBuffer() + фрейм для if внутри этого метода, flush() или interrupt() + 2 фрейма для if и interrupt0() + currentThread()+ checkAccess() с фреймом для if, currentThread(),... в зависимости от хода выполнения,  в рамках которого в указанные фреймы и в кучу записываются соответствующие данные
                    создается фрейм newLine() и фрейм try/catch + далее иерархично по ходу выполнения метода (смотри строкой выше)


        Во время выполнения программы возникают STW-паузы, в которые запускается сборщик мусора - GC в разных областях кучи: чаще всего в young genetation (самые частые сбоки в Eden, затем в Survivor0, затем в Survivor1), очень редко в  old generation. GC строит граф достижимых объектов и обходит его, удаляя недостижимые объекты из кучи (например, в первую очередь удалится объект, на который uselessVar).
        */
    }
}
