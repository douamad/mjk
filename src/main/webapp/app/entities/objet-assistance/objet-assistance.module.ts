import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MjkSharedModule } from 'app/shared/shared.module';
import { ObjetAssistanceComponent } from './objet-assistance.component';
import { ObjetAssistanceDetailComponent } from './objet-assistance-detail.component';
import { ObjetAssistanceUpdateComponent } from './objet-assistance-update.component';
import { ObjetAssistanceDeleteDialogComponent } from './objet-assistance-delete-dialog.component';
import { objetAssistanceRoute } from './objet-assistance.route';

@NgModule({
  imports: [MjkSharedModule, RouterModule.forChild(objetAssistanceRoute)],
  declarations: [
    ObjetAssistanceComponent,
    ObjetAssistanceDetailComponent,
    ObjetAssistanceUpdateComponent,
    ObjetAssistanceDeleteDialogComponent,
  ],
  entryComponents: [ObjetAssistanceDeleteDialogComponent],
})
export class MjkObjetAssistanceModule {}
