import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MjkSharedModule } from 'app/shared/shared.module';
import { CreanceComponent } from './creance.component';
import { CreanceDetailComponent } from './creance-detail.component';
import { CreanceUpdateComponent } from './creance-update.component';
import { CreanceDeleteDialogComponent } from './creance-delete-dialog.component';
import { creanceRoute } from './creance.route';

@NgModule({
  imports: [MjkSharedModule, RouterModule.forChild(creanceRoute)],
  declarations: [CreanceComponent, CreanceDetailComponent, CreanceUpdateComponent, CreanceDeleteDialogComponent],
  entryComponents: [CreanceDeleteDialogComponent],
})
export class MjkCreanceModule {}
