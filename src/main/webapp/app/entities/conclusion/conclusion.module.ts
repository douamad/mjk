import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MjkSharedModule } from 'app/shared/shared.module';
import { ConclusionComponent } from './conclusion.component';
import { ConclusionDetailComponent } from './conclusion-detail.component';
import { ConclusionUpdateComponent } from './conclusion-update.component';
import { ConclusionDeleteDialogComponent } from './conclusion-delete-dialog.component';
import { conclusionRoute } from './conclusion.route';

@NgModule({
  imports: [MjkSharedModule, RouterModule.forChild(conclusionRoute)],
  declarations: [ConclusionComponent, ConclusionDetailComponent, ConclusionUpdateComponent, ConclusionDeleteDialogComponent],
  entryComponents: [ConclusionDeleteDialogComponent],
})
export class MjkConclusionModule {}
