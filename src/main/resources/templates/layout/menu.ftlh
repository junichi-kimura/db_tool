<#macro default>
<!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          <li class="nav-item <#if isActive("/")>menu-open</#if>">
            <a href="/" class="nav-link <#if isActive("/")>active</#if>">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Dashboard
              </p>
            </a>
          </li>
          <li class="nav-item <#if isActive("/database/")>menu-is-opening menu-open</#if>">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-database"></i>
              <p>
                DB
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="/database/table/" class="nav-link <#if isActive("/database/table")>active</#if>">
                  <i class="far fa-circle nav-icon"></i>
                  <p>テーブル情報</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/database/a5m2_er/" class="nav-link <#if isActive("/database/a5m2_er")>active</#if>">
                  <i class="far fa-circle nav-icon"></i>
                  <p>A5M2のER図</p>
                </a>
              </li>
            </ul>
          </li>
          <li class="nav-item <#if isActive("/master/")>menu-is-opening menu-open</#if>">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-database"></i>
              <p>
                マスタ
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="/master/project/" class="nav-link <#if isActive("/master/project/")>active</#if>">
                  <i class="far fa-circle nav-icon"></i>
                  <p>プロジェクト</p>
                </a>
              </li>
            </ul>
          </li>
        </ul>
      </nav>
      <!-- /.sidebar-menu -->
</#macro>

<#function isActive path>
	<#if path == "/">
		<#return u.getUri() == "/">
	</#if>
	<#return u.getUri()?starts_with(path)>
</#function>