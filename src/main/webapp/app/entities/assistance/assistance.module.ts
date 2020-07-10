import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MjkSharedModule } from 'app/shared/shared.module';
import { AssistanceComponent } from './assistance.component';
import { AssistanceDetailComponent } from './assistance-detail.component';
import { AssistanceUpdateComponent } from './assistance-update.component';
import { AssistanceDeleteDialogComponent } from './assistance-delete-dialog.component';
import { assistanceRoute } from './assistance.route';

@NgModule({
  imports: [MjkSharedModule, RouterModule.forChild(assistanceRoute)],
  declarations: [AssistanceComponent, AssistanceDetailComponent, AssistanceUpdateComponent, AssistanceDeleteDialogComponent],
  entryComponents: [AssistanceDeleteDialogComponent],
})
export class MjkAssistanceModule {}
