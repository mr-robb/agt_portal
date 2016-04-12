<%@ page import="java.sql.ResultSet" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>

<%
    String rfc = request.getParameter("rfc"); if (rfc == null ) rfc = "";
    String jndiName = "AASEG";
    //String jndiName = "jdbc/db_agentes";
    Statement st = null;
    ResultSet rs = null;
    Connection connection = null;
    try {
        Context ctx = new InitialContext();
        DataSource dataSource = ( DataSource ) ctx.lookup(jndiName);
        connection = dataSource.getConnection();
				String sql=
            "SELECT " +
                "E.ID_AGENTE," +
                "E.ANO_MES," +
                "E.RFC," +
                "E.TOT_IMP_PAGO," +
                "E.TOT_IMP_ISR," +
                "E.TOT_IMP_IVA_RET," +
                "E.TOT_IMP_IVA_PAG," +
                "E.IMP_DEDUCCION," +
                "E.IMP_SUBTOTAL," +
                "E.IMP_TOTAL, " +
                "E.IMP_TOTAL, " +
                "E.IMP_TOTAL, " +
                "E.IMP_TOTAL, " +
                "E.ANIO_MES_DIA " +
            "FROM AGT_FEL_ENVIO E " +
                ", ( " +
                    "SELECT ID_AGENTE,ANIO_MES_DIA,RFC " +
                    "FROM AGT_FEL_ADMON " +
                    "WHERE RFC='" + rfc + "' " +
                    "   AND ESTATUS='PEND' " + 
                    "GROUP BY ID_AGENTE,ANIO_MES_DIA,RFC " +
                    ") A " +
            "WHERE E.RFC = '" + rfc + "' " +
            "    AND E.FH_RESPUESTA IS NULL  " + 
            "    AND E.ANIO_MES_DIA = A.ANIO_MES_DIA (+) " +
            "    AND E.ID_AGENTE = A.ID_AGENTE (+) " +
            "    AND E.RFC = A.RFC (+) " +
            "    AND A.ID_AGENTE IS NULL " +
            "ORDER BY E.ANO_MES ASC";
// System.out.println(sql);
        st = connection.createStatement();
        rs = st.executeQuery(sql);
        while ( rs.next() ) {
            String row =
                "AIN930126LM6|" +
                "ASEGURADORA INTERACCIONES S.A. DE C.V. GRUPO FINANCIERO INTERACCIONES|" +
                "Paseo de la Reforma|" +
                "383|" +
                "Piso 5|" +
                "CUAUHTEMOC|" +
                "MEXICO|" +
                "CUAUHTEMOC|" +
                "Distrito Federal|" +
                "MEXICO|" +
                "06500|";
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                String field = rs.getString(i + 1);
                if ( field == null ) field = "";
                row += field + "|";
            }

            out.println(row);
        }
    } catch(Exception ex) {ex.printStackTrace();}
      finally {
        if( rs != null ){
            rs.close();
        }

        if( st != null ){
            st.close();
        }
        if( connection != null ){
            connection.close();
        }
    }
%>
