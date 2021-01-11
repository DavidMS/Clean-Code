package org.dms.cleancode.cfunctions;


/***
 El objetivo del curso no es proporcionar una fomación científica y reglada sobre la mejor manera de prgramar.
 A diferencia de un curso para aprender un lenguaje de programación determinado, las enseñanzas de este curso no se
 basan en reglas definidas y universales, más bien, este curso pretende concienciar al alumno de la importancia
 de escribir código de calidad y fomentar el debate sobre cuál es la forma más apropiada de alcanzar este objetivo,
 generando código legible, mantenible y que aporte valor y facilite y haga más agradable tanto el trabajo propio
 como el de cualquier otro desarrollador que tenga que lidiar con el código desarrollado en un futuro.
 ***/
public class Functions {
    public static String testableHtml(PageData pageData, boolean includeSuiteSetup) throws Exception {
        WikiPage wikiPage = pageData.getWikiPage();
        StringBuffer buffer = new StringBuffer();
        if(pageData.hasAttribute("Test")) {
            if(includeSuiteSetup) {
                WikiPage suiteSetup = PageCrawlerImpl.getInheritedPage(SuiteResponder.SUITE_SETUP_NAME, wikiPage);
                if(suiteSetup != null) {
                    WikiPagePath pagePath = suiteSetup.getPageCrawler().getFullPath(suiteSetup);
                    String pagePathName = PathParser.render(pagePath);
                    buffer.append("!include -setup .")
                            .append(pagePathName)
                            .append("\n");
                }
            }
            WikiPage setup = PageCrawlerImpl.getInheritedPage(SuiteResponder.SUITE_SETUP_NAME, wikiPage);
            if(setup != null) {
                WikiPagePath setupPath = wikiPage.getPageCrawler().getFullPath(setup);
                String setupPathName = PathParser.render(setupPath);
                buffer.append("!include -setup .")
                        .append(setupPathName)
                        .append("\n");
            }
            buffer.append(pageData.getContent());
            if(pageData.hasAttribute("Test")) {
                WikiPage teardown = PageCrawlerImpl.getInheritedPage(SuiteResponder.TEAR_DOWN, wikiPage);
                if(teardown != null) {
                    WikiPagePath tearDownPath = wikiPage.getPageCrawler().getFullPath(teardown);
                    String tearDownPathName = PathParser.render(tearDownPath);
                    buffer.append("\n")
                            .append("!include -teardown .")
                            .append(tearDownPathName)
                            .append("\n");
                }
                if(includeSuiteSetup) {
                    WikiPage suiteTeardown = PageCrawlerImpl.getInheritedPage(SuiteResponder.TEAR_DOWN, wikiPage);
                    if(suiteTeardown != null) {
                        WikiPagePath pagePath = suiteTeardown.getPageCrawler().getFullPath(suiteTeardown);
                        String pagePathName = PathParser.render(pagePath);
                        buffer.append("!include -teardown .")
                                .append(pagePathName)
                                .append("\n");
                    }
                }
            }
        }
        pageData.setContent(buffer.toString());
        return pageData.getHtml();
    }

    public static String renderPageWithSetupsAndTeardowns(PageData pageData, boolean isSuite) throws Exception {
        boolean isTestPage = pageData.hasAttribute("Test");
        if(isTestPage) {
            WikiPage testPage = pageData.getWikiPage();
            StringBuffer newPageContent = new StringBuffer();
            // TODO: Implement this function
            includeSetupPages(testPage, newPageContent, isSuite);
            newPageContent.append(pageData.getContent());
            // TODO: Implement this function
            includeTeardownPages(testPage, newPageContent, isSuite);
            pageData.setContent(newPageContent.toString());
        }
        return pageData.getHtml();
    }

    // TAMAÑO REDUCIDO
    // la primera regla de las funciones es que deben ser de tamaño reducido.
    // La segunda es que deben ser todavía más reducidas.
    // Las funciones deben tener una longitud aproximada de 20 líneas.
    public static String renderPageWithSetupsAndTeardowns(PageData pageData, boolean isSuite) throws Exception {
        // TODO: Implement this function
        if(isTestPage(pageData)) {
            // TODO: Implement this function
            // TODO: Question: Difference between method and function
            includeSetupAndTeardownPages(pageData, isSuite);
        }
        return pageData.getHtml();
    }

    // BLOQUES Y SANGRADO
    // if, else, while y similar deben tener una línea de longitud que seguramente será la invocación de otra función
    // p.Ej: if(isTestPage(pageData)) --> Añade valor documental al ser el nombre de función descriptivo
    // El nivel de sangrado de una función no debe ser mayor de uno o dos.

    // HACER UNA COSA
    // LAS FUNCIONES SOLO DEBEN HACER UNA COSA. DEBEN HACERLO BIEN Y DEBE SER LO ÚNICO QUE HAGAN
    // TODO: Información sobre principios SOLID
    // Si una función solo realiza los pasos situados un nivel por debajo del nombre de la función, entonces hace
    // una cosa.

    // SECCIONES EN FUNCIONES
    // Las funciones que hacen una sola cosa no se pueden dividir en secciones.

    // UN NIVEL DE ABSTRACCIÓN POR FUNCIÓN
    // Asegúrese de que las instrucciones de la función se encuentran en el mismo nivel de abstracción.

    // LEER CÓDIGO DE ARRIBA A ABAJO: LA REGLA DESCENDENTE
    // El objetivo es que el código se lea como un texto de arriba a abajo.
    // - Para incluir configuraciones y detalles, incluimos configuraciones, después del contenido de la página de prueba
    //   y por último los detalles
    // - Para incluir las confiuraciones, incluimos la configuración de suite si se trata de una suite, y después la
    //   configuración convencional
    // - Para incluir la configuración de suite, buscamos la jerarquía principal de la página SuiteSetUp y añadimos
    //   una instrucción include con la ruta de dicha página
    // - Para buscar la jerarquía principal...


    // INSTRUCCIONES SWITCH
    public Money calculatePay(Employee e) throws InvalidEmployeeType {
        switch(e.type) {
            case COMISSIONED:
                return calculateCommisionedPay(e);
            case HOURLY:
                return calculateHourlyPay(e);
            case SALARIED:
                return calculateSalariedPay(e);
            default:
                throw new InvalidEmployeeType(e.type);
        }
    }
    // Este método incumple el SRP y el OCP
    // Solución... Factoria Abstracta
    public abstract class Employee {
        public abstract boolean isPayDay();
        public abstract Money calculatePay();
        public abstract void deliveryPay(Money pay);
    }

    public interface EmployeeFactory {
        public Employee makeEmployee(EmployeeRecord r) throws InvalidEmployeeType;
    }

    public class EmployeeFactoryImpl implements EmployeeFactory {
        public Employee makeEmployee(EmployeeRecord r) throws InvalidEmployeeType {
            switch(r.type) {
                case COMMISIONED:
                    return new CommisionedEmployee(r);
                case HOURLY:
                    return new HourlyEmployee(r);
                case SALARIED:
                    return new SalariedEmployee(r);
                default:
                    throw new InvalidEmployeeType(r.type);
            }
        }
    }

    // USAR NOMBRES DESCRIPTIVOS

    // ARGUMENTOS DE FUNCIONES
    // El número ideal de argumentos para una función es cero. Después uno (monádico) y dos (diádico). Siempre que sea
    // posible, evite la presencia de tres argumentos (triádico). Más de tres argumentos (poliádico) requiere una
    // justificación especial.

    // FORMAS MONÁDICAS HABITUALES
    // TODO: Añadir algunos ejemplos

    // ARGUMENTOS DE INDICADOR
    // Los argumentos de indicador son horribles. Indica que la función hace más de una cosa, algo si es true y otra
    // cosa si es false.

    // FUNCIONES DIÁTICAS
    // TODO: Añadir algunos ejemplos

    // TRIADAS
    // TODO: Añadir algunos ejemplos

    // OBJETO DE ARGUMENTO
    Circle makeCircle(double x, double y, double radius);
    Circle makeCircle(Point center, double radius);

    // Listas de argumentos
    String.format("%s worked %.2f hours", name, hours); // Diádica
    public String format(String format, Object... args);

    // VERBOS Y PALABRAS CLAVE
    // En formato monádico, la función y el argumento deben formar un par de verbo y sustantivo. [write(name)]
    // MEJORA: writeField(name)  ## así indicamos que name es un field
    // assertEquals(expected, actual) --> MEJORA: assertExpectedEquals(expected, actual)

    // SIN EFECTOS SECUNDARIOS
    public class UserValidator {
        private Cryptographer cryptographer;

        public boolean checkPassword(String userName, String password) {
            User user = UserGateway.findByName(userName);
            if(user != User.NULL) {
                String codedPhrase = user.getPhraseEncodedByPassword();
                String phrase = cryptographer.decrypt(codedPhrase, password);
                if("Valid Password".equals(phrase)) {
                    Session.initialize(); // INICIALIZA LA SESSION Y NO DEBERÍA HACER ESO
                    return true;
                }
            }
            return false;
        }
    }

    // ARGUMENTOS DE SALIDA
    // appendFooter(s); --> Es confuso. Es un argumento de entrada que en realidad es de salida.
    // public void appendFooter(StringBuffer report) --> Esto lo aclara pero hay que ir a la definición
    // report.appendFooter(); --> Es mas claro y conciso.

    // SEPARACIÓN DE CONSULTAS DE COMANDO
    // Las funciones deben hacer algo o responder a algo pero no ambas cosas.

}

class PageData {
    private WikiPage wikiPage;
    private String content;
    private String html;

    public WikiPage getWikiPage() {
        return wikiPage;
    }

    public boolean hasAttribute(String name) {
        return true;
    }

    public String getContent() {
        return "";
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHtml() {
        return html;
    }
}

class WikiPage {
    private PageCrawlerImpl pageCrawler;

    public PageCrawlerImpl getPageCrawler() {
        return pageCrawler;
    }
}

class WikiPagePath {}

class PageCrawlerImpl {

    public WikiPagePath getFullPath(WikiPage wikiPage) {
        return new WikiPagePath();
    }

    public static WikiPage getInheritedPage(SuiteResponder name, WikiPage wikiPage) {
        return new WikiPage();
    }
}

class PathParser {
    public static String render(WikiPagePath wikiPagePath) {
        return "";
    }
}

enum SuiteResponder {
    SUITE_SETUP_NAME,
    TEAR_DOWN
}
