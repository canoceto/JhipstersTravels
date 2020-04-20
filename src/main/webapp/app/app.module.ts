import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import './vendor';
import { SpringBootDemoSharedModule } from 'app/shared/shared.module';
import { SpringBootDemoCoreModule } from 'app/core/core.module';
import { SpringBootDemoAppRoutingModule } from './app-routing.module';
import { SpringBootDemoHomeModule } from './home/home.module';
import { SpringBootDemoEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    SpringBootDemoSharedModule,
    SpringBootDemoCoreModule,
    SpringBootDemoHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    SpringBootDemoEntityModule,
    SpringBootDemoAppRoutingModule
    // MDBBootstrapModule.forRoot()
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent]
})
export class SpringBootDemoAppModule {}
