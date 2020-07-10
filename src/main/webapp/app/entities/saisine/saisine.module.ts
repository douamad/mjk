import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MjkSharedModule } from 'app/shared/shared.module';
import { SaisineComponent } from './saisine.component';
import { SaisineDetailComponent } from './saisine-detail.component';
import { SaisineUpdateComponent } from './saisine-update.component';
import { SaisineDeleteDialogComponent } from './saisine-delete-dialog.component';
import { saisineRoute } from './saisine.route';

@NgModule({
  imports: [MjkSharedModule, RouterModule.forChild(saisineRoute)],
  declarations: [SaisineComponent, SaisineDetailComponent, SaisineUpdateComponent, SaisineDeleteDialogComponent],
  entryComponents: [SaisineDeleteDialogComponent],
})
export class MjkSaisineModule {}
