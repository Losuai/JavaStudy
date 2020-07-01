package custom_annotations;

import java.lang.reflect.Method;

public class ParseCustomAnnotation {
    public static void main(String[] args) {
        Class<Hero> clazz = Hero.class;
        MyEntity myEntity = clazz.getAnnotation(MyEntity.class);
        if (myEntity == null){
            System.out.println("该类不是实体类");
        }else{
            System.out.println("Hero是实体类");
            MyTable myTable = clazz.getAnnotation(MyTable.class);
            String tableName = myTable.name();
            System.out.println("表名为：" + tableName);
            Method[] methods = clazz.getMethods();
            Method primaryKeyMethod = null;
            for (Method m: methods){
                MyId myId = m.getAnnotation(MyId.class);
                if (myId != null){
                    primaryKeyMethod = m;
                    break;
                }
            }
            if (primaryKeyMethod != null){
                System.out.println("找到主键： " + formatMethod(primaryKeyMethod.getName()));
                MyGeneratedValue myGeneratedValue = primaryKeyMethod.getAnnotation(MyGeneratedValue.class);
                System.out.println("自增长策略：" + myGeneratedValue.strategy());
                MyColumn myColumn = primaryKeyMethod.getAnnotation(MyColumn.class);
                System.out.println("对应字段如： " + myColumn.value());
            }
            System.out.println("非主键-------如下");
            for (Method m: methods){
                if (m == primaryKeyMethod)
                    continue;
                MyColumn myColumn = m.getAnnotation(MyColumn.class);
                if (myColumn != null){
                    System.out.println("属性：" + formatMethod(m.getName()) + " 对应数据库字段：" + myColumn.value());
                }
            }
        }

    }
    private static String formatMethod(String methodName){
        String result = methodName;
        System.out.println("----" + result);
        result = result.replace("get", "");
        result = result.replace("id", "");
        if (result.length()<=1){
            return result.toLowerCase();
        }else {
            return result.substring(0, 1).toLowerCase()+result.substring(1, result.length());
        }
    }
}
