<#macro bind path>
    <#if htmlEscape?exists>
        <#assign status = springMacroRequestContext.getBindStatus(path, htmlEscape)>
    <#else>
        <#assign status = springMacroRequestContext.getBindStatus(path)>
    </#if>
    <#-- assign a temporary value, forcing a string representation for any
    kind of variable. This temp value is only used in this macro lib -->
    <#if status.value?exists && status.value?is_boolean>
        <#assign stringStatusValue=status.value?string>
    <#else>
        <#assign stringStatusValue=status.value?default("")>
    </#if>
</#macro>

<#macro formInput path name="" attributes="" fieldType="text"  id="" class="" value="" placeholder="">
    <#local status = springMacroRequestContext.getBindStatus(path)>
    <#if status.errorMessages?? && status.errorMessages?size != 0>
      <#local class = class + " is-invalid">
    </#if>
    <@compress single_line=true>
    <input type="${fieldType}" 
      <#if id?has_content>id="${id}"</#if>
      <#if class?has_content>class="${class}"</#if>
      <#if placeholder?has_content>placeholder="${placeholder}"</#if>
      <#if name?has_content>name="${name}"<#else>name="${name}"</#if> 
      <#if value?has_content>value="${value}"</#if>
      <#if attributes?has_content>${attributes}</#if>
    />
    </@compress>

	<#list status.errorMessages as error>
      <span <#if id?has_content>id="${id}-error"</#if> class="error invalid-feedback" style="display:block">${error}</span>
    </#list>
</#macro>

<#macro formSingleSelect path options optionValue firstEmpty=true name="" selectedValue=-1 id="" class="" attributes="">
    <@u.bind path=path/>
    <#if status.errorMessages?? && status.errorMessages?size != 0>
      <#local class = class + " is-invalid">
    </#if>
    <select id="${id}" class="${class}" name="${name}" <#if attributes??>${attributes}</#if>>
        <#if options?is_hash>
            <#if firstEmpty><option value="">---</option></#if>
            <#list options?keys as value>
            <option value="${value.id}" <#if selectedValue?? && selectedValue?string == value.id?string>selected</#if>>${options[value]}</option>
            </#list>
        <#else>
            <#if firstEmpty><option value="">---</option></#if>
            <#list options as value>
            <option value="${value.id}" <#if selectedValue?? && selectedValue?string == value.id?string>selected</#if>>${value[optionValue]}</option>
            </#list>
        </#if>
    </select>
    <#list status.errorMessages as error>
      <span <#if id?has_content>id="${id}-error"</#if> class="error invalid-feedback" style="display:block">${error}</span>
    </#list>
</#macro>

<#macro enumSingleSelect path enumName name selectedValue=-1 empty=true id="" class="form-control" attributes="">
    <#local options = enum["${ENUM_PACKAGE}.${enumName}"]?values />
    <@u.bind path=path/>
    <#if status.errorMessages?? && status.errorMessages?size != 0>
      <#local class = class + " is-invalid">
    </#if>
    <select id="${id}" class="${class}" name="${name}" ${attributes}>
        <#if empty>
          <option value="">---</option>
        </#if>
        <#list options as o>
          <option value="${o.id}"<#if selectedValue?? && selectedValue?string == o.id?string>selected</#if>>${o.getName()}</option>
        </#list>
    </select>
    <#list status.errorMessages as error>
      <span <#if id?has_content>id="${id}-error"</#if>class="error invalid-feedback" style="display:block">${error}</span>
    </#list>
</#macro>

<#macro showErrors path>
	<@u.bind path=path/>
	<#list status.errorMessages as error>
      <p class="text-danger">${error}</p>
    </#list>
</#macro>

<#macro checkSelected value>
    <#if stringStatusValue?is_number && stringStatusValue == value?number>selected="selected"</#if>
    <#if stringStatusValue?is_string && stringStatusValue == value>selected="selected"</#if>
</#macro>

<#function getUri>
	<#return springMacroRequestContext.requestUri>
</#function>