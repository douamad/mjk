import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MjkSharedModule } from 'app/shared/shared.module';
import { RequerantComponent } from './requerant.component';
import { RequerantDetailComponent } from './requerant-detail.component';
import { RequerantUpdateComponent } from './requerant-update.component';
import { RequerantDeleteDialogComponent } from './requerant-delete-dialog.component';
import { requerantRoute } from './requerant.route';

@NgModule({
  imports: [MjkSharedModule, RouterModule.forChild(requerantRoute)],
  declarations: [RequerantComponent, RequerantDetailComponent, RequerantUpdateComponent, RequerantDeleteDialogComponent],
  entryComponents: [RequerantDeleteDialogComponent],
})
export class MjkRequerantModule {}
