package com.hndist.server.util;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * map文件格式化读写
 */

public class MapFileRw {

    /** mapserver URL */
    public static final String HTTP_192_168_1_119_9966_CGI_BIN_MAPSERV_EXE = "http://127.0.0.1:9966/cgi-bin/mapserv.exe";

    /**
     * 格式化读取 获取
     */
    public static List<String> mapFileR(String mfp) throws IOException {
        List<String> list=new ArrayList<>();

        FileInputStream inputStream = new FileInputStream(mfp);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str = null;
        boolean comment=false;
        int i=1;

        while((str = bufferedReader.readLine()) != null){
            if(str.equals("|||||*/")){
                break;
            }
            if(str.equals("/*|||||")){
                comment=true;
                continue;

            }
            if(comment){
                list.add(str.split("##########")[0]);
                i++;
            }

        }
        list.remove(0);
        return list;
    }

    /**
     * web数据元
     */
    public static String webmetaData(String abpath){
        return "METADATA\n" +
                "       \"wfs_title\" \"WMS Demo Server\"\n" +
                "\t\t\"wfs_onlineresource\" \"" + HTTP_192_168_1_119_9966_CGI_BIN_MAPSERV_EXE + "?MAP=" +abpath+"&\"\n" +
                "\t\t\"ows_onlineresource\" \"" + HTTP_192_168_1_119_9966_CGI_BIN_MAPSERV_EXE + "?MAP=" +abpath+"&\"\n" +
                "\t\t\"wfs_srs\" \"EPSG:4326\"\n" +
                "\t\t\"wfs_enable_request\" \"*\"\n" +

                "\t\t\"wms_srs\"   \t\t\t\"EPSG:4326\"\n" +
                "\t\t\"wms_allow_getmap_without_styles\"  \t\t\t\"true\"\n" +
                "\t\t\"wms_enable_request\" \t\"*\"\n" +
                "\t\t\"wms_feature_info_mime_type\" \"text/html\"\n" +
                "\t\t\"ows_enable_request\" \"*\"\n" +
                "\t\t\"wms_format\" \"image/png\"\n" +
                "    \n" +
                "END";
    }

    /**
     * 图层数据元
     */
    public static String lymetaData(){
        return "\nMETADATA\n" +
                "  \"wfs_srs\" \"EPSG:4326\"\n" +
                "  \"wfs_enable_request\" \"*\"\n" +
                "  \"gml_include_items\" \"all\"\n" +
                "  \"gml_featureid\" \"osm_id\"\n" +
                "  \"gml_geometries\" \"none\"\n" +
                "  \n" +
                "  \"wms_version\" \"1.3.0\"\n" +
                "  \"wms_srs\" \"EPSG:4326\"\n" +
                "  \"wms_enable_request\" \"*\"\n" +
                "  \"ows_enable_request\" \"*\"\n" +
                "  \"wms_feature_info_mime_type\" \"text/html\"\n" +
                "  \"wms_format\" \"image/png\"\n" +
                "END\n";
    }

    /**
     * 格式化写入
     */
    public static void mapFileW(){

//        Connection connection= H2DataBaseUtill.getPoolConnt();
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet rs1 = statement.executeQuery("select * from MAPCONFIG");
//            while (rs1.next()){
//                System.out.println(rs1.getString("erwei"));
//            }
//            connection.close();
//            statement.close();
//        }catch (Exception ignored){
//        }
        //插入
//        try {
//            String sql="insert into MAPCONFIG(fwmc,fwdz,erwei,sanwei) values(?)";
//            PreparedStatement ptmt = connection.prepareStatement(sql);
//            ptmt.setString(1, fwmc);
//
//            ptmt.execute();
//            ptmt.close();
//            connection.close();
//
//        }catch (Exception ignored){
//        }

    }

//    public ResFormat queryMfp() throws SQLException {
//        Connection connection= H2DataBaseUtill.getPoolConnt();
//        Statement statement = connection.createStatement();
//        statement.execute("Drop table MAPCONFIG");
//        statement.close();
//        connection.close();
//
//        return ok();
//    }






    public static void main(String[] args) throws IOException {
//        System.out.println(mapFileR("D:/a2533b30ec8c44d58c3e500fec8da366.map"));
        System.out.println(webmetaData("H:\\ms4w\\apps\\local-demo\\test.map"));


    }

}