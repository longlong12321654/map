import psycopg2
import cx_Oracle

class Pg:
    def __init__(self, usr, pwd, dburl,port,dbname):  # 类的构造函数，用于初始化类的内部状态，为类的属性设置默认值
        self.usr = usr
        self.pwd = pwd
        self.dburl = dburl
        self.port=port
        self.dbname = dbname

    # 获取所有表
    def get_table_name(self):
        conn = psycopg2.connect(dbname=self.dbname, user=self.usr, password=self.pwd,host=self.dburl, port=self.port)
        cur = conn.cursor()
        cur.execute("select tablename from pg_tables where schemaname='public'")  # 获取所有的表名
        tablenames = cur.fetchall()
        # print(tablenames)
        table_list = []  # 表名
        for tablename in tablenames:
            table_list.append(tablename[0])
        return table_list

    #获取空间类型的表
    def get_geom_table(self):
        global spatial_type
        table_data=[]

        try:
            conn = psycopg2.connect(dbname=self.dbname, user=self.usr, password=self.pwd,host=self.dburl, port=self.port)
            cur = conn.cursor()
            cur.execute("SELECT * FROM geometry_columns")
            geo_exit = cur.fetchall()
            for geo in geo_exit:

                if geo[6] in ['MULTIPOLYGON','POLYGON']:
                    spatial_type='面'
                elif geo[6] in ['MULTILINESTRING','LINESTRING']:
                    spatial_type='线'
                elif geo[6] in ['MULTIPOINT','POINT']:
                    spatial_type='点'
                table_data.append({"dbname":geo[0],"db_schame":geo[1],"table_name":geo[2],"srid":geo[5],"spatial_type":spatial_type})

            cur.close()
            conn.close()
            return {"code": 200, 'data': table_data}
        except:
            return {"code": 404, 'data': "连接失败！请检查数据库连接信息"}

    # 表数据
    def dtable(self, tbname):
        tb = []
        conn = psycopg2.connect(dbname=self.dbname, user=self.usr, password=self.pwd,
                                host=self.dburl, port=5432)
        cur = conn.cursor()



        cur.execute('select * from ' + tbname + ' limit 30')
        for rl in cur.fetchall():
            tb.append(list(rl))
        tab = []
        # sqlf="select * from information_schema.columns where table_schema='public' and table_name='{0}'".format(tbname)
        sql_pg2 = "SELECT a.attname as name FROM pg_class as c, pg_attribute as a where c.relname = '{0}' and a.attrelid = c.oid and a.attnum > 0".format(
            tbname)

        cur.execute(sql_pg2)  # 获取一个表中所有的列名
        colunmnames = cur.fetchall()
        for da in colunmnames:
            tab.append(da[0])

        cur.close()
        conn.close()
        return [tb, tab]


# oracle数据库
class Orcl:
    def __init__(self, usr, pwd, instance):  # 类的构造函数，用于初始化类的内部状态，为类的属性设置默认值
        self.usr = usr
        self.pwd = pwd
        self.instnce = self.usr + '/' + self.pwd + '@' + instance

    #获取表数据
    def conn(self,tbname):
        tb = []
        conn = cx_Oracle.connect(self.instnce)
        cursor = cx_Oracle.Cursor(conn)
        cursor.execute('select * from '+tbname+' WHERE ROWNUM<30')
        for rl in cursor.fetchall():
            tb.append(list(rl))

        tab = []
        cursor.execute("select a.COLUMN_NAME from user_col_comments b left join user_tab_columns a on a.Table_Name=b.Table_Name and a.COLUMN_NAME=b.COLUMN_NAME where a.Table_Name='{0}' and b.Table_Name='{0}'".format(tbname))
        data = cursor.fetchall()
        for da in data:
            tab.append(da[0])
        cursor.close()
        conn.close()
        return [tb, tab]

# t = Orcl('ZSTPUSR', 'ZSTPUSR', '192.168.1.119:1521/orcl')
# t.conn()

if __name__ == "__main__":
    t = Pg('postgres', 'wwwddosi110', '192.168.1.119',5432, 'hndist')
    print(t.get_geom_table())
    # t = Orcl('ZSTPUSR', 'ZSTPUSR', '192.168.1.119:1521/orcl')
    # print(t.conn("POINTSET"))



