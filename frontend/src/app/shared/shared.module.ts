import { NgModule } from '@angular/core';
import { PRIMENG_IMPORTS } from './primeng-imports';
import { ServiceModule } from './services/service.module';

@NgModule({
    imports: [
        PRIMENG_IMPORTS,
        ServiceModule
    ],
    providers: [],
    exports: [
        PRIMENG_IMPORTS,
        ServiceModule
    ]
})
export class SharedModule { }
