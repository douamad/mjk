import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MjkSharedModule } from 'app/shared/shared.module';
import { MaisonComponent } from './maison.component';
import { MaisonDetailComponent } from './maison-detail.component';
import { MaisonUpdateComponent } from './maison-update.component';
import { MaisonDeleteDialogComponent } from './maison-delete-dialog.component';
import { maisonRoute } from './maison.route';

@NgModule({
  imports: [MjkSharedModule, RouterModule.forChild(maisonRoute)],
  declarations: [MaisonComponent, MaisonDetailComponent, MaisonUpdateComponent, MaisonDeleteDialogComponent],
  entryComponents: [MaisonDeleteDialogComponent],
})
export class MjkMaisonModule {}
