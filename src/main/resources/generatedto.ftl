/**
 *
 *
 * @author ${authorName}
 * @version 1.0
 * @since ${createTime}
 */
@Data
public class ${className}{
    <#list params as param>
    /**
     * ${param.commentDetail}
     */
    private ${param.resultType} ${param.variableName};

    </#list>
}